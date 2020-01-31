package com.training.coauth.entity;

import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;


@RedisHash("notifications")
public class Notification {

    @Id
    private String fcmToken;

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
