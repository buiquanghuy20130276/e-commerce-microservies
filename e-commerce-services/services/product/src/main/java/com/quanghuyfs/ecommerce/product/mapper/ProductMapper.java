package com.quanghuyfs.ecommerce.product.mapper;

import com.quanghuyfs.ecommerce.product.model.Category;
import com.quanghuyfs.ecommerce.product.model.Product;
import com.quanghuyfs.ecommerce.product.request.ProductRequest;
import com.quanghuyfs.ecommerce.product.response.ProductPurchaseResponse;
import com.quanghuyfs.ecommerce.product.response.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequest request) {

        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .availableQuantity(request.availableQuantity())
                .price(request.price())
                .category(Category.builder()
                        .id(request.id())
                        .build())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getDescriptioon()
        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, int quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
