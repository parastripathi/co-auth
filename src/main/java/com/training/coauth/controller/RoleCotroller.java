package com.training.coauth.controller;


import com.training.coauth.Utility.UserUtils;
import com.training.coauth.dto.ApiResponse;
import com.training.coauth.dto.RoleRequest;
import com.training.coauth.dto.TokenRequest;
import com.training.coauth.entity.Channel;
import com.training.coauth.entity.Role;
import com.training.coauth.entity.User;
import com.training.coauth.repository.ChannelRepository;
import com.training.coauth.repository.RoleRepository;
import com.training.coauth.repository.UserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/role")
public class RoleCotroller {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    UserUtils userUtils;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/updateRole")
    public ResponseEntity<?> updateUserRole(HttpServletRequest request,@RequestBody RoleRequest roleRequest){

        Role role = new Role();
        User user = new User();
        String requestTokenHeader = request.getHeader("Authorization");
        String token = requestTokenHeader.substring(7);
        Claims claims = userUtils.loadClaimsByToken(token);

        Long userId = (Long.valueOf(String.valueOf(claims.get("id"))));
        Long channelId = roleRequest.getChannel_channel_id();
        String userRole = roleRequest.getRole();
        Channel channel = channelRepository.findById(channelId).get();
        user.setUserId(userId);

        role.setChannel(channel);
        role.setUsers(user);
        role.setRole(userRole);

        roleRepository.save(role);

        return ResponseEntity.ok(new ApiResponse(true,"Role was assigned"));

    }


}
