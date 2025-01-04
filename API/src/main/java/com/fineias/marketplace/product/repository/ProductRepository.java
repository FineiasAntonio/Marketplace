package com.fineias.marketplace.product.repository;

import com.fineias.marketplace.product.core.model.Product;
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
            nativeQuery = true,
            value = "SELECT * FROM products WHERE products.product_name LIKE %:keyword% OR products.description LIKE %:keyword%"
    )
    Page<Product> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM products WHERE products.seller_id = :sellerId"
    )
    List<Product> findBySeller(@Param("sellerId") UUID userId);

}
