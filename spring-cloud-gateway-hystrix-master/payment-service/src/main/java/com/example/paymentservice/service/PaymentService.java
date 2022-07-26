package com.example.paymentservice.service;

import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.repository.PaymentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment savePayment(Payment payment) throws JsonProcessingException {
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        log.info("payment service request {}", new ObjectMapper().writeValueAsString(payment));
        return paymentRepository.save(payment);
    }

    public String paymentProcessing() {
        return new Random().nextBoolean() ? "success" : "false";
    }

    public Payment findPaymentHistoryOrderId(int orderId) throws JsonProcessingException {
        Payment payment = paymentRepository.findByOrderId(orderId);
        log.info("OrderService request {}", new ObjectMapper().writeValueAsString(payment));
        return payment;
    }
}
