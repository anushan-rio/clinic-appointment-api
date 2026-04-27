package com.clinic.clinic_api.notification;

import org.springframework.stereotype.Component;

@Component
public class ConsoleNotification implements NotificationStrategy {

    public void send(String message) {
        System.out.println("NOTIFICATION: " + message);
    }
}