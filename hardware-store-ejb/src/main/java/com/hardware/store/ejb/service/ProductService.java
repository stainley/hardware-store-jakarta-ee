package com.hardware.store.ejb.service;

import com.hardware.store.ejb.interceptors.LoggingInterceptor;
import com.hardware.store.ejb.interceptors.annotations.LoggingInterceptorBinding;
import com.hardware.store.ejb.config.RedisConfig;
import com.hardware.store.jpa.entities.Product;
import com.hardware.store.jpa.repository.ProductRepository;
import io.lettuce.core.api.sync.RedisCommands;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.interceptor.Interceptors;
import redis.clients.jedis.json.DefaultGsonObjectMapper;
import redis.clients.jedis.json.JsonObjectMapper;

import java.util.List;
import java.util.Optional;

@Stateless
public class ProductService {

    @EJB
    private ProductRepository productRepository;

    @Inject
    private RedisConfig redisConfig;

    private final static JsonObjectMapper objectMapper = new DefaultGsonObjectMapper();


    @Interceptors({LoggingInterceptor.class})
    @LoggingInterceptorBinding
    public Product getProduct(Long id) {
        RedisCommands<String, String> redisCommands = redisConfig.getCommands();
        String cachedProduct = redisCommands.get("product:" + id);

        if (cachedProduct != null) {
            return deserializedProduct(cachedProduct);
        }

        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            redisCommands.set("product:" + id, serializeProduct(product.get()));
            return product.get();
        }
        return null;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Interceptors({LoggingInterceptor.class})
    @LoggingInterceptorBinding
    public Product saveProduct(Product product) {
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        return productRepository.save(product);
    }

    @Interceptors({LoggingInterceptor.class})
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }


    private String serializeProduct(Product product) {
        return objectMapper.toJson(product);
    }

    private Product deserializedProduct(String productJson) {
        return objectMapper.fromJson(productJson, Product.class);
    }
}
