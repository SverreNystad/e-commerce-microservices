package com.ecommerce.orderline;

import org.springframework.stereotype.Service;

import com.ecommerce.order.Order;


@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.id())
                .quantity(request.quantity())
                .order(
                    Order.builder()
                        .id(request.orderId())
                    .build()
                )
                .productId(request.productId())
                .build();
    }

    public OrderLineDTO toOrderLineDTO(OrderLine orderLine) {
        return new OrderLineDTO(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }

}
