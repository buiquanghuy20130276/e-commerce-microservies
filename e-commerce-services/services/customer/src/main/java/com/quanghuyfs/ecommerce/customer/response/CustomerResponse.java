package com.quanghuyfs.ecommerce.customer.response;

import com.quanghuyfs.ecommerce.customer.model.Address;

public record CustomerResponse(
        Long id,
        String firstname,

        String lastname,

        String email,
        Address address
) {
}
