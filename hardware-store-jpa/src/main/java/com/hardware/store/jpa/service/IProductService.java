package com.hardware.store.jpa.service;


import com.hardware.store.jpa.entities.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    Product getProduct(Long id);

    List<Product> getAllProducts();

    Product saveProduct(Product product);

    void deleteProduct(Long id);

}
