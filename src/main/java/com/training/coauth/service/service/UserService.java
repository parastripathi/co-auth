package com.training.coauth.service.service;


import com.training.coauth.entity.User;

import java.util.Optional;


public interface UserService {
    public Optional<User> findById(Long id);

    boolean existsByEmail(String email);

    User save(User user);

    String getToken(String userEmail, String userPassword);


    void setRedis(String token, String userEmail);

}
