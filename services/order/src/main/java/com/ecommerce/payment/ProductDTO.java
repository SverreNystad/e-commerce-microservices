package com.ecommerce.payment;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductDTO(
    Integer id,
    @NotNull(message = "Product name is required")
    String name,
    @NotNull(message = "Product description is required")
    String description,
    @Positive(message = "Available quantity must be positive")
    double availableQuantity,
    @Positive(message = "Price must be positive")
    BigDecimal price,
    @NotNull(message = "Category ID is required")
    Integer categoryId,
    String categoryName,
    String categoryDescription
) {}
