package com.quanghuyfs.ecommerce.payment.kafka;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        Long id,
        @NotNull(message = "Firstname is required")
        String firstname,
        @NotNull(message = "Lastname is required")

        String lastname,
        @NotNull(message = "Email is required")
        @Email(message = "Your email is not correctly formatted")
        String email
) {
}
