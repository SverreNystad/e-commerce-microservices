package com.ecommerce.orderline;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
public class OrderLineController {
    
    private final OrderLineService orderLineService;
    
    @GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrderLineDTO>> findByOrderId(
        @PathVariable("order-id") Integer orderId) {
        
        List<OrderLineDTO> orderLines = orderLineService.findAllByOrderId(orderId);
        return ResponseEntity.ok(orderLines);
    }
}
