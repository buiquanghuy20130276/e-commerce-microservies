package com.quanghuyfs.ecommerce.response;

import com.quanghuyfs.ecommerce.model.PaymentMethod;
import com.quanghuyfs.ecommerce.request.PurchaseRequest;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;
@Validated
public record OrderResponse(
        Long id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Long customerId
) {
}
