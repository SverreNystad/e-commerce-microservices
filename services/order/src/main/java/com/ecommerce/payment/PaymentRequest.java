package com.ecommerce.payment;

import java.math.BigDecimal;

import com.ecommerce.customer.CustomerDTO;
import com.ecommerce.order.PaymentMethod;

public record PaymentRequest(
    PaymentMethod paymentMethod,
    BigDecimal amount,
    Integer orderId,
    String orderReference,
    CustomerDTO customer
) {
}
