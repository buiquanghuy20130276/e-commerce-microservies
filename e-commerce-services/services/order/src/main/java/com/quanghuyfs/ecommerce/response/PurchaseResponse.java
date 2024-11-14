package com.quanghuyfs.ecommerce.response;

import java.math.BigDecimal;

public record PurchaseResponse(
        Long productId,
        String name,
        String description,
        BigDecimal price,
        int quantity
) {
}
