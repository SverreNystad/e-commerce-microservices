package com.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseRequest(
    @NotNull(message = "Product is mandatory")
    Integer productId,
    @Positive(message = "Quantity must be positive")
    double quantity
) {}
