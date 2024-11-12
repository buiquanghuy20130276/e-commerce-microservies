package com.quanghuyfs.ecommerce.product.service;

import com.quanghuyfs.ecommerce.product.exception.ProductPurchaseException;
import com.quanghuyfs.ecommerce.product.mapper.ProductMapper;
import com.quanghuyfs.ecommerce.product.repository.ProductRepository;
import com.quanghuyfs.ecommerce.product.request.ProductPurchaseRequest;
import com.quanghuyfs.ecommerce.product.request.ProductRequest;
import com.quanghuyfs.ecommerce.product.response.ProductPurchaseResponse;
import com.quanghuyfs.ecommerce.product.response.ProductResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Long createProduct(ProductRequest request) {
        var product = mapper.toProduct(request);
        return repository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        var productIds = request.stream()
                .map(ProductPurchaseRequest::productId).toList();
        var storedProducts = repository.findAllByIdInOrderById(productIds);
        if (productIds.size()!= storedProducts.size()){
            throw new ProductPurchaseException("One or more products does not exist"+productIds);
        }

        var storeRequest = request.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchaseProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i=0;i<storedProducts.size();i++){
            var product =storedProducts.get(i);
            var productRequest = storeRequest.get(i);
            if (product.getAvailableQuantity()<productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient stock quantity for product with with ID:"+productRequest.productId());
            }
            var newAvailableQuantity =  product.getAvailableQuantity()-productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            repository.save(product);
            purchaseProducts.add(mapper.toProductPurchaseResponse(product,productRequest.quantity()));
        }
        return null;
    }

    public ProductResponse findById(Long id) {
        return repository.findById(id)
                .map(mapper::toProductResponse)
                .orElseThrow(()->new EntityNotFoundException(String.format("Product is not found with ID: ",id)));

    }

    public List<ProductResponse> findAllProducts() {
        return repository.findAll().stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
