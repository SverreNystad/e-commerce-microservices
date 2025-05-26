package com.ecommerce.order;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<Integer> createOrder(
        @RequestBody @Valid OrderRequest request) {
        
        return ResponseEntity.ok(
            service.createOrder(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll() {
        return ResponseEntity.ok(
            service.findAll()
        );
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderDTO> findById(
        @PathVariable("order-id") Integer orderId) {
        return ResponseEntity.ok(
            service.findById(orderId)
        );
    }


}