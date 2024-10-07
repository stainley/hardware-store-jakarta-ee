package com.hardware.store.ejb.listeners;

import com.hardware.store.ejb.service.AuditLogService;
import com.hardware.store.ejb.service.EmailService;
import com.hardware.store.jpa.entities.Product;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;

@Stateless
public class ProductListener {

    @Inject
    private EmailService emailService;

    @Inject
    private AuditLogService auditLogService;

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
        System.out.println("onProductDeleted: " + product.getName());
    }
}
