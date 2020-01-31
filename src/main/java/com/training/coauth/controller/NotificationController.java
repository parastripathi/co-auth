package com.training.coauth.controller;

import com.training.coauth.dto.ApiResponse;
import com.training.coauth.dto.FcmRequest;
import com.training.coauth.dto.FcmResponse;
import com.training.coauth.dto.RoleRequest;
import com.training.coauth.entity.Notification;
import com.training.coauth.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @PostMapping("/fcmtoken")
    public ResponseEntity<?> updateFcmToken(HttpServletRequest request, @RequestBody FcmRequest fcmRequest){

        String requestTokenHeader = request.getHeader("Authorization");
        String token = requestTokenHeader.substring(7);
        notificationService.setFcmToken(token,fcmRequest.getFcmToken());

        return ResponseEntity.ok(new ApiResponse(true,"FCM token is set"));

    }

    @GetMapping("/getFcmByUser/{id}")
    public FcmResponse getFCMDetails(HttpServletRequest request, @PathVariable Long id){
        List<Notification> fcmTokens = notificationService.getFcmUser(id);
        FcmResponse fcmResponse = new FcmResponse();
        fcmResponse.setFcmTokens(fcmTokens);

        return fcmResponse;

    }



}
