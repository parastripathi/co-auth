package com.training.coauth.service.Impl;

import com.training.coauth.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private RedisTemplate< String, Object > template;

    public void setValue( final String key, final String value ) {
        template.opsForValue().set( key, value );
    }

    @Override
    public void setFcmToken(String token, String fcmToken) {
        setValue(token,fcmToken);
    }
}
