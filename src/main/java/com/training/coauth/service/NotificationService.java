package com.training.coauth.service;

import com.training.coauth.entity.Notification;

import java.util.List;

public interface NotificationService {
    void setFcmToken(String token, String fcmToken);

    List<Notification> getFcmUser(Long id);
}
