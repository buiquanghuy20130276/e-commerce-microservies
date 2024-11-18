package com.quanghuyfs.ecommerce.product.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Product> products;
}
