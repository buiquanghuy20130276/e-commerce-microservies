package com.quanghuyfs.ecommerce.customer.request;

import com.quanghuyfs.ecommerce.customer.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(String id,
                              @NotNull(message = "Customer firstname is required")
                              String firstname,
                              @NotNull(message = "Customer lastname is required")

                              String lastname,
                              @NotNull(message = "Customer email is required")
                              @Email(message = "Customer email is not a valid email address")

                              String email,
                              Address address) {

}
