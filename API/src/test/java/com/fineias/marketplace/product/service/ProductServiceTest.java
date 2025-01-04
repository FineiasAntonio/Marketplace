package com.fineias.marketplace.product.service;

import com.fineias.marketplace.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findProductByKeyword() {
    }

    @Test
    void findProductById() {
    }

    @Test
    void registerProduct() {
    }

    @Test
    void updateProduct() {
    }
}