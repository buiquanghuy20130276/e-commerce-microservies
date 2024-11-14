package com.quanghuyfs.ecommerce.response;

public record CustomerResponse(
        Long id,
        String firstname,
        String lastname,
        String email

) {
}
