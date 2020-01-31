package com.training.coauth.service.Impl;

import com.training.coauth.entity.Notification;
import com.training.coauth.repository.NotificationRepository;
import com.training.coauth.security.TokenProvider;
import com.training.coauth.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {


    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public void setFcmToken(String token, String fcmToken) {
        Long id = tokenProvider.getUserIdFromToken(token);
        Notification notification = new Notification();
        notification.setFcmToken(fcmToken);
        notification.setUserId(id);

        notificationRepository.save(notification);

    }

    @Override
    public List<Notification> getFcmUser(Long id) {
        List<Notification> fcmTokens = notificationRepository.findAllByUserId(id);
        return fcmTokens;
    }


}
