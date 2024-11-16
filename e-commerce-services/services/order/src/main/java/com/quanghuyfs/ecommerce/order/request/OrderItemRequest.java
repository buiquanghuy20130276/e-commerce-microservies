package com.quanghuyfs.ecommerce.order.request;

public record OrderItemRequest(
        Long id,
        Long orderId,
        Long productId,
        int quantity
) {
}
