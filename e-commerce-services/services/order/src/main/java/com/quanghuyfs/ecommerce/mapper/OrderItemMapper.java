package com.quanghuyfs.ecommerce.mapper;

import com.quanghuyfs.ecommerce.model.Order;
import com.quanghuyfs.ecommerce.model.OrderItem;
import com.quanghuyfs.ecommerce.request.OrderItemRequest;
import com.quanghuyfs.ecommerce.response.OrderItemResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderItemMapper {
    public OrderItem toOrderItem(OrderItemRequest orderItemRequest) {
    return OrderItem.builder()
            .id(orderItemRequest.id())
            .quantity(orderItemRequest.quantity())
            .order(Order.builder()
                    .id(orderItemRequest.id())
                    .build())
            .productId(orderItemRequest.productId())
            .build();
    }

    public OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
        return new OrderItemResponse(
                orderItem.getProductId(),
                orderItem.getQuantity()
        );
    }
}
