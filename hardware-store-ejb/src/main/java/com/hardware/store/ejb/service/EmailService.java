package com.hardware.store.ejb.service;

import jakarta.ejb.Stateless;

@Stateless
public class EmailService {


    public void sendNotification(String to, String subject, String message) {
        System.out.println("Email sent to " + to + " with subject " + subject + " and message " + message);
    }
}
