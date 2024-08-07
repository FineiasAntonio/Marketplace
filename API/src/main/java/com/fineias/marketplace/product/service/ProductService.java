package com.fineias.marketplace.product.service;

import com.fineias.marketplace.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    Page<Product> findBySearchTerm(String searchTerm, int page, int size);

}
