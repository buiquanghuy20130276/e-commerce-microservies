package com.quanghuyfs.ecommerce.kafka;

import com.quanghuyfs.ecommerce.model.PaymentMethod;
import com.quanghuyfs.ecommerce.response.CustomerResponse;
import com.quanghuyfs.ecommerce.response.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
