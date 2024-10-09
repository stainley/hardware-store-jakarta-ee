package com.hardware.store.ejb.service;

import jakarta.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void sendNotification(String to, String subject, String message) {
        logger.info("Email sent to {} with subject {} and message {}.", to, subject, message);
    }
}
