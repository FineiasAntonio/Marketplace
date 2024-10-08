package com.fineias.marketplace.product.repository;

import com.fineias.marketplace.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

//    Page<Product> findBySearchTerm(String searchTerm, Pageable pageable);

}
