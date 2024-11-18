package com.quanghuyfs.ecommerce.email;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EmailTemplate {
    PAYMENT_CONFIRMATION("payment-confirmation.html", "payment successfully processed"),
    ORDER_CONFIRMATION("order-confirmation.html", "Order confirmation");

    @Getter
    private final String template;
    @Getter
    private final String subject;


}
