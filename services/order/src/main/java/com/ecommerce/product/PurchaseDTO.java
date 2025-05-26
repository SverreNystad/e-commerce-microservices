package com.ecommerce.product;

import java.math.BigDecimal;

public record PurchaseDTO(
    Integer productId,
    String name,
    String description,
    BigDecimal price,
    Integer quantity
) {

}
