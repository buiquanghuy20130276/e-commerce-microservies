package com.quanghuyfs.ecommerce.payment.kafka.notification;

import com.quanghuyfs.ecommerce.payment.model.PaymentMethod;

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
