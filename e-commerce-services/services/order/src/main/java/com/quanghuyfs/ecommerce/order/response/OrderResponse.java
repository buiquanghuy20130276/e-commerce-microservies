package com.quanghuyfs.ecommerce.order.response;

import com.quanghuyfs.ecommerce.order.model.PaymentMethod;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Validated
public record OrderResponse(
        Long id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {
}
