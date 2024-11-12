package com.quanghuyfs.ecommerce.product.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
@Validated
public record ProductRequest(
         Long id,
         @NotNull(message = "Product name is required")
         String name,
         @NotNull(message = "Product description is required")
         String description,
         @Positive(message = "Product quantity should be positive")
         int availableQuantity,
         @Positive(message = "Product price should be positive")
         BigDecimal price,
         @NotNull(message = "Price category is required")
         Long categoryId) {


}
