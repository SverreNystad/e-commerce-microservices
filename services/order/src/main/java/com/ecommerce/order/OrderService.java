package com.ecommerce.order;


import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.customer.CustomerClient;
import com.ecommerce.customer.CustomerDTO;
import com.ecommerce.exception.BusinessException;
import com.ecommerce.kafka.OrderConfirmation;
import com.ecommerce.kafka.OrderProducer;
import com.ecommerce.orderline.OrderLineRequest;
import com.ecommerce.orderline.OrderLineService;
import com.ecommerce.product.ProductClient;
import com.ecommerce.product.PurchaseDTO;
import com.ecommerce.product.PurchaseRequest;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public Integer createOrder(OrderRequest request) {
        // Validate the customer (OpenFeign example)
        CustomerDTO customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException(
                        "Customer not found with id: " + request.customerId()
                ));

        // Purchase the products (RestTemplate example)
        List<PurchaseDTO> purchasedProducts = productClient.purchaseProducts(request.products());

        // Persist the order
        Order savedOrder = orderRepository.save(mapper.toOrder(request));

        // Persist the order lines
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOderLine(
                new OrderLineRequest(
                    null,
                    savedOrder.getId(),
                    purchaseRequest.productId(),
                    purchaseRequest.quantity()
                    )
            );
        }

        // Start payment process
        // TODO: Implement payment processing logic here
        
        // Notify the customer --> notification service (kafka)
        orderProducer.sendOrderConfirmation(
            new OrderConfirmation(
                request.reference(),
                request.amount(),
                request.paymentMethod(), 
                customer,
                purchasedProducts
            )
        );
        return savedOrder.getId();
    }

    public List<OrderDTO> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .toList();
    }

    public OrderDTO findById(Integer id) {
        return orderRepository.findById(id)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
    }
}
