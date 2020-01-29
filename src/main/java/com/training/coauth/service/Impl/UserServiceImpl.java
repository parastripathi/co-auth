package com.training.coauth.service.Impl;


import com.training.coauth.entity.User;
import com.training.coauth.repository.UserRepository;
import com.training.coauth.security.TokenProvider;
import com.training.coauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired private RedisTemplate< String, Object > template;

    public Object getValue( final String key ) {
        return template.opsForValue().get( key );
    }

    public void setValue( final String key, final String value ) {
        template.opsForValue().set( key, value );
    }


    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }


    @Override
    public void setRedis(String token, String userEmail) {
        setValue(token,userEmail);
    }

    @Override
    public String getToken(String email, String userPassword) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        userPassword
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);

        return token;
    }
}
