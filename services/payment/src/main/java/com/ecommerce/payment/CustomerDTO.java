package com.ecommerce.payment;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Validated
public record CustomerDTO(
    String id,
    @NotNull(message = "First name cannot be null")
    String firstName,
    @NotNull(message = "Last name cannot be null")
    String lastName,
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    String email
) {

}
