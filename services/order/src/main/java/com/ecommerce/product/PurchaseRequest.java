package com.ecommerce.product;

import java.math.BigDecimal;

import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
    Integer productId,
    @Positive(message = "Quantity must be positive")
    double quantity,
    @Positive(message = "Price must be positive")
    BigDecimal price
) {

}
