package com.ecommerce.kafka;

import java.math.BigDecimal;
import java.util.List;

import com.ecommerce.customer.CustomerDTO;
import com.ecommerce.order.PaymentMethod;

import com.ecommerce.product.PurchaseDTO;

public record OrderConfirmation(
    String orderReference,
    BigDecimal totalAmount,
    PaymentMethod paymentMethod,
    CustomerDTO customerDetails,
    List<PurchaseDTO> purchaseDetails
) {

}
