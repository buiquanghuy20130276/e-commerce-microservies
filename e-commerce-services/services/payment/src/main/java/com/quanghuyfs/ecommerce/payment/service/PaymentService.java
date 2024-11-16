package com.quanghuyfs.ecommerce.payment.service;

import com.quanghuyfs.ecommerce.payment.mapper.PaymentMapper;
import com.quanghuyfs.ecommerce.payment.notification.NotificationProducer;
import com.quanghuyfs.ecommerce.payment.notification.PaymentNotificationRequest;
import com.quanghuyfs.ecommerce.payment.request.PaymentRequest;
import com.quanghuyfs.ecommerce.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;
    public Long createPayment(PaymentRequest request) {
        var payment = repository.save(mapper.toPayment(request));

        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstname(),
                        request.customer().lastname(),
                        request.customer().email()
                )
        );

        return payment.getId();
    }
}
