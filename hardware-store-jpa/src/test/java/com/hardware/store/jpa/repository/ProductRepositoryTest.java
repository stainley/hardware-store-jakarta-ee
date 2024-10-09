package com.hardware.store.jpa.repository;

import com.hardware.store.jpa.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProductRepositoryTest {

    @Mock
    private EntityManager em;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private TypedQuery<Product> query;

    @Mock
    private CriteriaQuery<Product> criteriaQuery;

    @InjectMocks
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Product.class)).thenReturn(criteriaQuery);
    }

    @Test
    void testFindById() {
        // Arrange
        Product product = new Product(1L, "Product 1");
        when(em.find(Product.class, 1L)).thenReturn(product);

        // Act
        Optional<Product> result = productRepository.findById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(product, result.get());
    }

    @SuppressWarnings("unchecked")
    @Test
    void testFindAll() {
        // Arrange
        List<Product> products = List.of(new Product(1L, "Product 1"), new Product(2L, "Product 2"));
        Root<Product> root = criteriaQuery.from(Product.class);
        criteriaQuery.select(root);

        when(query.getResultList()).thenReturn(products);
        when(em.createQuery(any(CriteriaQuery.class))).thenReturn(query);
        // Act
        List<Product> result = productRepository.findAll();

        // Assert
        assertEquals(products, result);
    }

    @Test
    void testSave() {
        // Arrange
        Product product = new Product(1L, "Product 1");
        when(em.merge(product)).thenReturn(product);

        // Act
        Product result = productRepository.save(product);

        // Assert
        assertEquals(product, result);
    }

    @Test
    void testDeleteById() {
        // Arrange
        Product product = new Product(1L, "Product 1");
        when(em.find(Product.class, 1L)).thenReturn(product);

        // Act
        productRepository.deleteById(1L);

        // Assert (no exception thrown)
        Mockito.verify(em).remove(product);
    }
}