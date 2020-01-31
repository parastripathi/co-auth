package com.training.coauth.dto;

import com.training.coauth.entity.Notification;

import java.util.List;

public class FcmResponse {

    private List<Notification> fcmTokens;

    public List<Notification> getFcmTokens() {
        return fcmTokens;
    }

    public void setFcmTokens(List<Notification> fcmTokens) {
        this.fcmTokens = fcmTokens;
    }
}
