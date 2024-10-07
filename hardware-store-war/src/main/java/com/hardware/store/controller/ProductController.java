package com.hardware.store.controller;

import com.hardware.store.ejb.service.ProductService;
import com.hardware.store.jpa.entities.Product;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class ProductController {

    @EJB
    private ProductService productService;

    private Product product;

    public void loadProduct() {
        product = productService.getProduct(1L);
    }

    public Product getProduct() {
        return product;
    }
}
