package com.training.coauth.controller;


import com.training.coauth.Utility.RoleUtils;
import com.training.coauth.Utility.UserUtils;
import com.training.coauth.config.AppProperties;
import com.training.coauth.dto.ApiResponse;
import com.training.coauth.dto.TokenRequest;
import com.training.coauth.dto.UserResponse;
import com.training.coauth.entity.User;
import com.training.coauth.security.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/jwt")
public class JwtController {

    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    RoleUtils roleUtils;

    @Autowired
    UserUtils userUtils;

    protected final Log logger = LogFactory.getLog(getClass());

    @PostMapping("/validateToken")
    public ResponseEntity<?> authenticateToken(HttpServletRequest request,@RequestBody TokenRequest tokenRequest){

        String requestTokenHeader = request.getHeader("Authorization");
        String token = requestTokenHeader.substring(7);
        if(tokenProvider.validateToken(token)){
            return ResponseEntity.ok(new ApiResponse(true,"The token is Valid"));
        }

        return ResponseEntity.ok(new ApiResponse(false,"The token is not Valid"));
    }

    @PostMapping("/getUserDetails")
    public ResponseEntity<?> getUserDetails(HttpServletRequest request, @RequestBody TokenRequest tokenRequest){

        String requestTokenHeader = request.getHeader("Authorization");
        String token = requestTokenHeader.substring(7);
        Long provider = tokenRequest.getProvider();
        Claims claims = userUtils.loadClaimsByToken(token);
        String userEmail = ((String) claims.get("email"));
        Long userId = (Long.valueOf(String.valueOf(claims.get("id"))));
        String userName = ((String) claims.get("name"));
        String userRole = roleUtils.getRoleById(userId,provider);

        return ResponseEntity.ok(new UserResponse(userId,userName,userEmail,userRole));

    }



}
