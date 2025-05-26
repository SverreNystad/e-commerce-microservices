package com.ecommerce.product;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseDTO(
    @NotNull(message = "Product is mandatory")
    Integer productId,
    String name,
    String description,
    BigDecimal price,
    @Positive(message = "Quantity must be positive")
    double quantity
) {
}
