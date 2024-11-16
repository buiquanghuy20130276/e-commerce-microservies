package com.quanghuyfs.ecommerce.order.service;

import com.quanghuyfs.ecommerce.order.mapper.OrderItemMapper;
import com.quanghuyfs.ecommerce.order.response.OrderItemResponse;
import com.quanghuyfs.ecommerce.order.repository.OrderItemRepository;
import com.quanghuyfs.ecommerce.order.request.OrderItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository repository;
    private final OrderItemMapper mapper;

    public Long saveOrderItem(OrderItemRequest orderItemRequest) {
        var order = mapper.toOrderItem(orderItemRequest);
        return repository.save(order).getId();
    }

    public List<OrderItemResponse> findAllByOrderId(Long orderId) {
        return repository.findAllByOrderId(orderId)
                .stream()
                .map(mapper::toOrderItemResponse)
                .collect(Collectors.toList());
    }
}
