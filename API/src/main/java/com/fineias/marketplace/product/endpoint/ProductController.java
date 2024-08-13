package com.fineias.marketplace.product.endpoint;

import com.fineias.marketplace.product.dto.ProductRegisterRequestDTO;
import com.fineias.marketplace.product.dto.ProductSummaryResponseDTO;
import com.fineias.marketplace.product.model.Product;
import com.fineias.marketplace.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductSummaryResponseDTO>> findBySearchTerm(
            @RequestParam(name = "searchTerm") String searchTerm,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
    ) {

        Page<ProductSummaryResponseDTO> response = productService.findBySearchTerm(searchTerm, page, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> findSingleProduct(@PathVariable(name = "productId") UUID productId) {

        Product productFound = productService.findProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(productFound);

    }

    @PostMapping
    public ResponseEntity<UUID> registerProduct(@RequestBody ProductRegisterRequestDTO productRegisterRequest) {

        UUID createdProductId = productService.registerProduct(productRegisterRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductId);

    }

}
