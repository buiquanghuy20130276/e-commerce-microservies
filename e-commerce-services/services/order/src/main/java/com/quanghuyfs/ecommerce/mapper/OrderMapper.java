package com.quanghuyfs.ecommerce.mapper;

import com.quanghuyfs.ecommerce.model.Order;
import com.quanghuyfs.ecommerce.request.OrderRequest;
import com.quanghuyfs.ecommerce.response.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrder(OrderRequest request) {
        return  Order.builder()
                .id(request.id())
                .customerId(request.customerId())
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
