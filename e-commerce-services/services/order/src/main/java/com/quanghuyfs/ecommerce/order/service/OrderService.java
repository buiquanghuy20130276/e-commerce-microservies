package com.quanghuyfs.ecommerce.order.service;

import com.quanghuyfs.ecommerce.order.customer.CustomerClient;
import com.quanghuyfs.ecommerce.order.product.ProductClient;
import com.quanghuyfs.ecommerce.order.exception.BusinessException;
import com.quanghuyfs.ecommerce.order.kafka.OrderConfirmation;
import com.quanghuyfs.ecommerce.order.kafka.OrderProducer;
import com.quanghuyfs.ecommerce.order.mapper.OrderMapper;
import com.quanghuyfs.ecommerce.order.payment.PaymentClient;
import com.quanghuyfs.ecommerce.order.payment.PaymentRequest;
import com.quanghuyfs.ecommerce.order.repository.OrderRepository;
import com.quanghuyfs.ecommerce.order.request.OrderItemRequest;
import com.quanghuyfs.ecommerce.order.request.OrderRequest;
import com.quanghuyfs.ecommerce.order.request.PurchaseRequest;
import com.quanghuyfs.ecommerce.order.response.OrderResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderItemService orderItemService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;
    public Long createOrder(OrderRequest request) {
        //check the customer
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(()-> new BusinessException("Cannot create order :: No customer exist with the provided id"));

        //purchase the product --> product-ms(RestTemplate)
       var purchaseProducts= this.productClient.purchaseProducts(request.products());


        //persit order
        var order = this.repository.save(mapper.toOrder(request));
        //start payment
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer

        );
        paymentClient.requestOrderPayment(paymentRequest);
        for (PurchaseRequest purchaseRequest : request.products()){
            orderItemService.saveOrderItem(
                    new OrderItemRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
        //send the order confirmation -->notification (kafka)
       orderProducer.sendOrderConfirmation(
               new OrderConfirmation(
                       request.reference(),
                       request.amount(),
                       request.paymentMethod(),
                       customer,
                       purchaseProducts
               )
       );
        return order.getId();

    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Long orderId) {
    return repository.findById(orderId)
            .map(mapper::fromOrder)
            .orElseThrow(()->new EntityNotFoundException("No order found with order ID "+orderId));

    }
}
