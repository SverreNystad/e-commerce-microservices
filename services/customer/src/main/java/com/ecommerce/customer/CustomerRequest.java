package com.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
    @NotNull(message = "ID cannot be null")
    String id,
    @NotNull(message = "Customer First Name cannot be null")
    String firstName,
    @NotNull(message = "Customer Last Name cannot be null")
    String lastName,
    @Email(message = "Customer Email should be valid") 
    String email,
    Address address
) {}
