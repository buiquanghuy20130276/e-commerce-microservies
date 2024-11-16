package com.quanghuyfs.ecommerce.customer.model;

import com.quanghuyfs.ecommerce.customer.model.Address;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Customer {
    @Id
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Address address;
}
