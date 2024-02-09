package com.github.andessonreis.api.service;

import org.springframework.stereotype.Service;

import com.github.andessonreis.api.domain.user.User;

@Service
public class NotificationService {
    
    public void sendNotification(User user, String message) {

        String email = user.getEmail();
        System.out.println(email + message);
    }
}
