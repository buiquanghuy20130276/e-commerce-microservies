package com.quanghuyfs.ecommerce.product.response;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Validated
public record ProductPurchaseResponse(
        @NotNull(message = "Product is mandatory")
        Long productId,
        String name,
        String description,
        BigDecimal price,
        int quantity

) {
}
