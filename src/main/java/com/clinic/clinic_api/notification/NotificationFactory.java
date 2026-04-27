package com.clinic.clinic_api.notification;

import org.springframework.stereotype.Component;

@Component
public class NotificationFactory {

    private final NotificationStrategy strategy;

    public NotificationFactory(NotificationStrategy strategy) {
        this.strategy = strategy;
    }

    public NotificationStrategy getStrategy() {
        return strategy;
    }
}