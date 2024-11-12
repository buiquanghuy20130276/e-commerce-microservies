package com.quanghuyfs.ecommerce.product.response;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String description,
        int availableQuantity,
        BigDecimal price,
        Long categoryId,
        String categoryDescription
) {
}
