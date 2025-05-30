package com.ecommerce.kafka.order;

import java.math.BigDecimal;
import java.util.List;

import com.ecommerce.order.PaymentMethod;

public class OrderConfirmation {
    String orderReference;
    BigDecimal totalAmount;
    PaymentMethod paymentMethod;
    CustomerDTO customer;
    List<Product> products;
    
}
