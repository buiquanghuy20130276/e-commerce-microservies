package com.quanghuyfs.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerResponse(
        Long id,
        String firstname,

        String lastname,

        String email,
        Address address
) {
}
