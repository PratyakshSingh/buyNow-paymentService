package com.example.paymentservice.services;

import com.example.paymentservice.paymentGateways.PaymentGateway;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public PaymentGateway paymentGateway;

    public PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }
    public String initiatePayment(String email, String phoneNumber, Long amount, String orderId) {
        return paymentGateway.generatePaymentLink(email, phoneNumber, amount, orderId);
    }
}
