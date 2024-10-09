package com.hardware.store.ejb.service.test.unit;

import com.hardware.store.ejb.config.RedisConfig;
import com.hardware.store.ejb.service.ProductService;
import com.hardware.store.jpa.entities.Product;
import com.hardware.store.jpa.repository.ProductRepository;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private RedisCommands<String, String> redisCommands;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        productService = new ProductService(new RedisConfig() {
            @Override
            public RedisCommands<String, String> getCommands() {
                return redisCommands;
            }
        });

        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(10.99);
    }

    @Test
    @DisplayName("testGetProduct_FromCache")
    void testGetProductFromCache() {
        //Given
        Mockito.when(redisCommands.get("product:1")).thenReturn("""
                {
                    "id": 1,
                    "name": "Test Product",
                    "description": "Test Description",
                    "price": 10.99
                }
                """);

        //When
        Product result = productService.getProduct(1L);

        //Then
        assertEquals(product.getName(), result.getName());
        Mockito.verify(productRepository, Mockito.never()).findById(Mockito.any());
        Mockito.verify(redisCommands, Mockito.times(1)).get("product:1");
    }


}