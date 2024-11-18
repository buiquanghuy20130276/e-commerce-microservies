package com.quanghuyfs.ecommerce.kafka.order;

public record Customer(
        Long id,
        String firstname,
        String lastname,
        String email
) {
}
