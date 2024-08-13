package com.fineias.marketplace.product.service;

import com.fineias.marketplace.product.dto.ProductRegisterRequestDTO;
import com.fineias.marketplace.product.dto.ProductSummaryResponseDTO;
import com.fineias.marketplace.product.exception.ProductNotFoundException;
import com.fineias.marketplace.product.model.Product;
import com.fineias.marketplace.product.repository.ProductRepository;
import com.fineias.marketplace.user.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Page<ProductSummaryResponseDTO> findBySearchTerm(String searchTerm, int page, int size) {

        Page<Product> result = productRepository.findBySearchTerm(searchTerm, PageRequest.of(page, size));

        return result.map(product ->
            new ProductSummaryResponseDTO(
                    product.getProductName(),
                    product.getDescription(),
                    product.getPrice()
            )
        );

    }

    @Override
    public Product findProduct(UUID productId) {

        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new ProductNotFoundException();
        }

        return product.get();
    }

    @Override
    @Transactional
    public UUID registerProduct(ProductRegisterRequestDTO registerRequest) {

        User authenticatedUser;

        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
           authenticatedUser = (User) principal;
        } else  {
            throw new RuntimeException("Principal isn't a instance of User");
        }

        Product product = Product
                .builder()
                .productName(registerRequest.productName())
                .category(registerRequest.category())
                .description(registerRequest.description())
                .price(registerRequest.price())
                .storage(registerRequest.storage())
                .sellerId(authenticatedUser.getUserId())
                .createdAt(Date.valueOf(LocalDate.now()))
                .build();

        Product registeredProduct = productRepository.save(product);
        return registeredProduct.getProductId();
    }
}
