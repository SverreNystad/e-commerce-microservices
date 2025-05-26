package com.ecommerce.order;

import java.util.List;

import com.ecommerce.product.PurchaseRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;


public record OrderRequest(
    Integer id,
    String reference,
    @Positive(message = "Total amount must be positive")
    BigDecimal amount,
    @NotNull(message = "Payment method must be present")
    PaymentMethod paymentMethod,
    @NotBlank(message = "Customer ID must not be blank")
    String customerId,
    @NotEmpty(message = "You must provide at least one product")
    List<PurchaseRequest> products
    ) {

}
