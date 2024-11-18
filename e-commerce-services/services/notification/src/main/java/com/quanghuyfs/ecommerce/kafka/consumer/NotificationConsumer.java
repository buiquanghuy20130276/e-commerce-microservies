package com.quanghuyfs.ecommerce.kafka.consumer;

import com.quanghuyfs.ecommerce.email.EmailService;
import com.quanghuyfs.ecommerce.kafka.order.OrderConfirmation;
import com.quanghuyfs.ecommerce.kafka.payment.PaymentConfirmation;
import com.quanghuyfs.ecommerce.notification.model.Notification;
import com.quanghuyfs.ecommerce.notification.model.NotificationType;
import com.quanghuyfs.ecommerce.notification.repository.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository repository;
    private final EmailService emailService;
    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message form payment-topic :: %s",paymentConfirmation));
        repository.save(
                Notification.builder()
                        .notificationType(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );
        //send email
        var customerName = paymentConfirmation.customerFirstname()+" "+paymentConfirmation.customerLastname();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );



    }
    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message form order-topic :: %s",orderConfirmation));
        repository.save(
                Notification.builder()
                        .notificationType(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );
        //send email
        var customerName = orderConfirmation.customer().firstname()+" "+orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );


    }
}
