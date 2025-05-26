package com.ecommerce.product;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<Integer> createProduct(ProductRequest product) {
        return ResponseEntity.ok(service.createProduct(product));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseDTO>> purchaseProduct(List<ProductPurchaseRequest> requests) {
        return ResponseEntity.ok(service.purchaseProduct(requests));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductDTO> getProductById(
        @PathVariable("product-id") Integer productId) {
        return ResponseEntity.ok(service.findById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(service.findAll());
    }
    
}
