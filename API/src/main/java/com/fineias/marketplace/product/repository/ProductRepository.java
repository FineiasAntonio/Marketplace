package com.fineias.marketplace.product.repository;

import com.fineias.marketplace.product.dto.ProductSummaryResponseDTO;
import com.fineias.marketplace.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query(
            value = "SELECT * FROM products WHERE products.product_name LIKE %:searchTerm% OR products.description LIKE %:searchTerm%",
            nativeQuery = true
    )
    Page<Product> findBySearchTerm(@Param("searchTerm") String searchTerm, Pageable pageable);

}
