package com.fineias.marketplace.product.endpoint;

import com.fineias.marketplace.product.core.dto.ProductRegisterRequestDTO;
import com.fineias.marketplace.product.core.dto.ProductSummaryResponseDTO;
import com.fineias.marketplace.product.core.dto.ProductUpdateRequestDTO;
import com.fineias.marketplace.product.core.model.Product;
import com.fineias.marketplace.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductSummaryResponseDTO>> findByKeyword(
            @RequestParam(name = "searchTerm") String searchTerm,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
    ) {
        Page<ProductSummaryResponseDTO> response = productService.findProductByKeyword(searchTerm, page, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> findProductById(@PathVariable(name = "productId") UUID productId) {

        Product productFound = productService.findProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(productFound);
    }

    @PostMapping
    public ResponseEntity<UUID> registerProduct(@RequestBody ProductRegisterRequestDTO productRegisterRequest) {

        UUID createdProductId = productService.registerProduct(productRegisterRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductId);
    }

    @PutMapping
    public ResponseEntity<Void> updateProduct(
            @RequestParam(name = "productId") UUID productId,
            @RequestBody ProductUpdateRequestDTO productUpdateRequest
    ) {
        productService.updateProduct(productId, productUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
