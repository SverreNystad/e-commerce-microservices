package com.ecommerce.orderline;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    
    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;

    public Integer saveOderLine(
        OrderLineRequest request
        ) {
        OrderLine orderLine = mapper.toOrderLine(request);
        return repository.save(orderLine).getId();
    }

    public List<OrderLineDTO> findAllByOrderId(Integer orderId) {
        return repository.findAllByOrderId(orderId)
            .stream()
            .map(mapper::toOrderLineDTO)
            .collect(Collectors.toList());
    }

}
