package com.quanghuyfs.ecommerce.product.controller;

import com.quanghuyfs.ecommerce.product.request.ProductPurchaseRequest;
import com.quanghuyfs.ecommerce.product.response.ProductPurchaseResponse;
import com.quanghuyfs.ecommerce.product.request.ProductRequest;
import com.quanghuyfs.ecommerce.product.response.ProductResponse;
import com.quanghuyfs.ecommerce.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Validated
public class ProductController {
    private final ProductService service;

    @PostMapping
    public ResponseEntity<Long> createProduct(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(service.createProduct(request));
    }
    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts (
            @RequestBody @Valid List <ProductPurchaseRequest> request
    ) {
        return ResponseEntity.ok(service.purchaseProducts(request));
    }
    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse>findById(@PathVariable("product-id") Long id){
        return ResponseEntity.ok(service.findById(id));
    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>>findAllProducts(){
        return ResponseEntity.ok(service.findAllProducts());
    }
}
