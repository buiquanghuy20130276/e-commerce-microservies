package com.quanghuyfs.ecommerce.service;

import com.quanghuyfs.ecommerce.mapper.OrderItemMapper;
import com.quanghuyfs.ecommerce.repository.OrderItemRepository;
import com.quanghuyfs.ecommerce.request.OrderItemRequest;
import com.quanghuyfs.ecommerce.response.OrderItemResponse;
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
