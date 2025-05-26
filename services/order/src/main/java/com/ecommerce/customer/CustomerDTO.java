package com.ecommerce.customer;

public record CustomerDTO(
    String id,
    String firstName,
    String lastName,
    String email,
    String phoneNumber
) {

}
