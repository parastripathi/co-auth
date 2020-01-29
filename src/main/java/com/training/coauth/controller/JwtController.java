package com.training.coauth.controller;


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

@RestController
@RequestMapping("/jwt")
public class JwtController {

    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    AppProperties appProperties;

    protected final Log logger = LogFactory.getLog(getClass());

    @PostMapping("/validateToken")
    public ResponseEntity<?> authenticateToken(@RequestBody TokenRequest tokenRequest){

        String token = tokenRequest.getToken();
        if(tokenProvider.validateToken(token)){
            return ResponseEntity.ok(new ApiResponse(true,"The token is Valid"));
        }

        return ResponseEntity.ok(new ApiResponse(false,"The token is not Valid"));
    }

    @PostMapping("/getUserDetails")
    public ResponseEntity<?> getUserDetails(@RequestBody TokenRequest tokenRequest){
        String token = tokenRequest.getToken();
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(appProperties.getAuth().getTokenSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            logger.error("Could not get all claims Token from passed token");
            claims = null;
            return ResponseEntity.ok("Could not get all claims Token from passed token");
        }
        
        String userEmail = ((String) claims.get("email"));
        Long userId = (Long.valueOf(String.valueOf(claims.get("id"))));

        return ResponseEntity.ok(new UserResponse(userId,userEmail));

    }



}
