package com.quanghuyfs.ecommerce.request;

public record OrderItemRequest(
        Long id,
        Long orderId,
        Long productId,
        int quantity
) {
}
