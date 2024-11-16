package com.quanghuyfs.ecommerce.payment.request;

import com.quanghuyfs.ecommerce.payment.kafka.Customer;
import com.quanghuyfs.ecommerce.payment.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Long id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Long orderId,
        String orderReference,
        Customer customer

) {
}
