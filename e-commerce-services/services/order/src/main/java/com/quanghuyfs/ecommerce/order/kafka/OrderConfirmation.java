package com.quanghuyfs.ecommerce.order.kafka;

import com.quanghuyfs.ecommerce.order.model.PaymentMethod;
import com.quanghuyfs.ecommerce.order.response.CustomerResponse;
import com.quanghuyfs.ecommerce.order.response.PurchaseResponse;

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
