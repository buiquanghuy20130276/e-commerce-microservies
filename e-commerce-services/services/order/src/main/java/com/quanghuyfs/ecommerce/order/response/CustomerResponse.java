package com.quanghuyfs.ecommerce.order.response;

public record CustomerResponse(
        Long id,
        String firstname,
        String lastname,
        String email

) {
}
