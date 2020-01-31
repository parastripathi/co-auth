package com.training.coauth.Utility;

import com.training.coauth.config.AppProperties;
import com.training.coauth.dto.ApiResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserUtils {

    @Autowired
    AppProperties appProperties;

    protected final Log logger = LogFactory.getLog(getClass());

    public Claims loadClaimsByToken(String token){
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(appProperties.getAuth().getTokenSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            logger.error("Could not get all claims Token from passed token");
            claims = null;
        }

        return claims;

    }

}
