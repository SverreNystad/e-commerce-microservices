package com.ecommerce.payment;

import org.springframework.stereotype.Service;

import com.ecommerce.notification.NotificationProducer;
import com.ecommerce.notification.PaymentNotificationRequest;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;
    
    public Integer createPayment(PaymentRequest request) {

        Payment payment = repository.save(mapper.toPayment(request));
        notificationProducer.sendPaymentNotification(
            new PaymentNotificationRequest(
                request.orderReference(),
                request.amount(),
                request.paymentMethod(),
                request.customer().firstName(),
                request.customer().lastName(),
                request.customer().email()
            )
        );
        return payment.getId();
    }

}
