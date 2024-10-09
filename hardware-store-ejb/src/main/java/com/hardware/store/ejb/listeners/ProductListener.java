package com.hardware.store.ejb.listeners;

import com.hardware.store.ejb.service.AuditLogService;
import com.hardware.store.ejb.service.EmailService;
import com.hardware.store.jpa.entities.Product;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class ProductListener {

    private static final Logger logger = LoggerFactory.getLogger(ProductListener.class);

    private EmailService emailService;

    private AuditLogService auditLogService;

    @Inject
    public ProductListener(EmailService emailService, AuditLogService auditLogService) {
        this.emailService = emailService;
        this.auditLogService = auditLogService;
    }

    public ProductListener() {
    }

    @PostPersist
    public void onProductCreated(Product product) {
        emailService.sendNotification("Inventory Team", "New product created", "Product: " + product.getName());
    }

    @PostUpdate
    public void onProductUpdated(Product product) {
        auditLogService.logChange("Product updated: " + product.getName());
    }

    @PostRemove
    public void onProductDeleted(Product product) {
        logger.info("onProductDeleted: {}", product.getName());
    }
}
