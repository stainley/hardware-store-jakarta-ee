package com.hardware.store.ejb.service;

import jakarta.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class AuditLogService {

    private final Logger logger = LoggerFactory.getLogger(AuditLogService.class);

    public void logChange(String message) {
        logger.info("Audit log: {}", message);
    }
}
