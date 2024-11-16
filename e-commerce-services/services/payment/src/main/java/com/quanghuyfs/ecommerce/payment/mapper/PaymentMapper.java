package com.quanghuyfs.ecommerce.payment.mapper;

import com.quanghuyfs.ecommerce.payment.model.Payment;
import com.quanghuyfs.ecommerce.payment.request.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayment(PaymentRequest request) {
        return Payment.builder().
                id(request.id())
                .orderId(request.orderId())
                .paymentMethod(request.paymentMethod())
                .amount(request.amount())
                .build();
    }
}
