package com.quanghuyfs.ecommerce.order.controller;

import com.quanghuyfs.ecommerce.order.response.OrderItemResponse;
import com.quanghuyfs.ecommerce.order.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order-items")
public class OrderItemController {
    private final OrderItemService service;
    @GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrderItemResponse>>findByOrderId(@PathVariable("order-id") Long orderId){
        return ResponseEntity.ok(service.findAllByOrderId(orderId));
    }
}
