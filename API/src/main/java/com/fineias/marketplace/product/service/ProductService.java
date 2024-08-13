package com.fineias.marketplace.product.service;

import com.fineias.marketplace.product.dto.ProductRegisterRequestDTO;
import com.fineias.marketplace.product.dto.ProductSummaryResponseDTO;
import com.fineias.marketplace.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ProductService {

    Page<ProductSummaryResponseDTO> findBySearchTerm(String searchTerm, int page, int size);
    Product findProduct(UUID productId);
    UUID registerProduct(ProductRegisterRequestDTO productRegisterRequest);

}
