package com.quanghuyfs.ecommerce.notification.request;

import com.quanghuyfs.ecommerce.kafka.payment.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {

}
