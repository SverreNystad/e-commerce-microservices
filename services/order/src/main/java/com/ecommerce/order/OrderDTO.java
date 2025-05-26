package com.ecommerce.order;

import java.math.BigDecimal;

public record OrderDTO(
    Integer id,
    String reference,
    BigDecimal totalAmount,
    PaymentMethod paymentMethod,
    String customerId
) {

}
