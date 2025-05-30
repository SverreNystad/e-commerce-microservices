package com.ecommerce.kafka.order;

public record CustomerDTO(
    String id,
    String firstName,
    String lastName,
    String email
) {
    
}
