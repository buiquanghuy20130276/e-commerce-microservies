package com.quanghuyfs.ecommerce.customer.response;

import com.quanghuyfs.ecommerce.customer.model.Address;

public record CustomerResponse(
        String id,
        String firstname,

        String lastname,

        String email,
        Address address
) {
}
