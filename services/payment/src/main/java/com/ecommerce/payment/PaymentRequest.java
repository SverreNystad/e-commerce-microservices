package com.ecommerce.payment;

import java.math.BigDecimal;

public record PaymentRequest(
    Integer id,
    PaymentMethod paymentMethod,
    BigDecimal amount,
    Integer orderId,
    String orderReference,
    CustomerDTO customer
) {
}
