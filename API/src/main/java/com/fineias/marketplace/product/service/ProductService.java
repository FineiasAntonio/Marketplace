package com.fineias.marketplace.product.service;

import com.fineias.marketplace.product.core.dto.ProductRegisterRequestDTO;
import com.fineias.marketplace.product.core.dto.ProductSummaryResponseDTO;
import com.fineias.marketplace.product.core.dto.ProductUpdateRequestDTO;
import com.fineias.marketplace.product.exception.ProductNotFoundException;
import com.fineias.marketplace.product.core.model.Product;
import com.fineias.marketplace.product.repository.ProductRepository;
import com.fineias.marketplace.user.gateway.port.UserPort;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Log4j2
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserPort userPort;

    public Page<ProductSummaryResponseDTO> findProductByKeyword(String searchTerm, int page, int size) {

        Page<Product> result = productRepository.findByKeyword(searchTerm, PageRequest.of(page, size));

        return result.map(product ->
            new ProductSummaryResponseDTO(
                    product.getProductName(),
                    product.getDescription(),
                    product.getPrice().floatValue()
            )
        );

    }

    public Product findProductById(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Transactional
    public UUID registerProduct(ProductRegisterRequestDTO registerRequest) {

        Product product = Product
                .builder()
                .productName(registerRequest.productName())
                .category(registerRequest.category())
                .description(registerRequest.description())
                .price(BigDecimal.valueOf(registerRequest.price()))
                .storage(registerRequest.storage())
                .sellerId(userPort.getAuthenticatedUser().getUserId())
                .createdAt(Date.valueOf(LocalDate.now()))
                .isActivated(registerRequest.activated())
                .build();

        Product registeredProduct = productRepository.save(product);
        log.info("A new product has been registered: {}", registeredProduct.getProductId());
        return registeredProduct.getProductId();
    }

    @Transactional
    public void updateProduct(UUID productId, ProductUpdateRequestDTO updateRequest) {
        Product productFound = productRepository.findById(productId)
                                                .orElseThrow(ProductNotFoundException::new);

        productFound.setProductName(updateRequest.productName());
        productFound.setCategory(updateRequest.category());
        productFound.setDescription(updateRequest.description());
        productFound.setPrice(BigDecimal.valueOf(updateRequest.price()));
        productFound.setStorage(updateRequest.storage());

        productRepository.save(productFound);
        log.info("The product {} has been updated", productFound.getProductId());
    }

}
