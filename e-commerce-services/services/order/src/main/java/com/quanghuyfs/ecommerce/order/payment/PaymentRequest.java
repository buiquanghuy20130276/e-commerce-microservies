package com.quanghuyfs.ecommerce.order.payment;

import com.quanghuyfs.ecommerce.order.model.PaymentMethod;
import com.quanghuyfs.ecommerce.order.response.CustomerResponse;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Long orderId,
        String orderReference,
        CustomerResponse customer
) {
}
