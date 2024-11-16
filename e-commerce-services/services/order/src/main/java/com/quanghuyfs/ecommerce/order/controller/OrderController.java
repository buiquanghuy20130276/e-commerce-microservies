package com.quanghuyfs.ecommerce.order.controller;

import com.quanghuyfs.ecommerce.order.request.OrderRequest;
import com.quanghuyfs.ecommerce.order.response.OrderResponse;
import com.quanghuyfs.ecommerce.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Validated
public class OrderController {
    private final OrderService service;
    @PostMapping
    public ResponseEntity<Long>createOrder(@RequestBody @Valid OrderRequest request){
        return ResponseEntity.ok(service.createOrder(request));
    }
    @GetMapping
    public ResponseEntity<List<OrderResponse>>findAll(){
        return  ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse>findById(@PathVariable("order-id") Long orderId){
        return ResponseEntity.ok(service.findById(orderId));
    }

}
