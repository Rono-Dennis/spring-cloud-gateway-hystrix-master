package com.example.paymentservice.controller;

import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @PostMapping("/doPayment")
    public Payment bookPay(@RequestBody Payment payment) throws JsonProcessingException {
        return paymentService.savePayment(payment);
    }


    @GetMapping("/{orderId}")
    public Payment findPaymentHistoryOrderId(@PathVariable int orderId) throws JsonProcessingException {
        return paymentService.findPaymentHistoryOrderId(orderId);
    }


}
