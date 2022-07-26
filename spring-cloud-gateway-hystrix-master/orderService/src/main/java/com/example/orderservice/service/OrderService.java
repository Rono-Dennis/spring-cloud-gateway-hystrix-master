package com.example.orderservice.service;

import com.example.orderservice.common.Payment;
import com.example.orderservice.common.TransactionRequest;
import com.example.orderservice.common.TransactionResponse;
import com.example.orderservice.entity.Languages;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.LanguageRepository;
import com.example.orderservice.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Logger;

@Service
@Slf4j
//        @RefreshScope
public class OrderService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private OrderRepository orderRepository;

//            private Logger log= (Logger) LoggerFactory.getLogger(OrderService.class);

//            @Value("${microservice.payment-service.endpoints.endpoint.uri}")
//            private String ENDPOINT_URL;

    public TransactionResponse saveOrder(TransactionRequest transactionRequest) throws JsonProcessingException {
        String response = "";
        Order order = transactionRequest.getOrder();
        Payment payment = transactionRequest.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        log.info("OrderService request {}", new ObjectMapper().writeValueAsString(transactionRequest));
        //rest call
        Payment paymentResponse = restTemplate.postForObject("http://PAYMENT-SERVICE/payment/doPayment", payment, Payment.class);
        log.info("Payment Service response from OrderService Rest call {}", new ObjectMapper().writeValueAsString(paymentResponse));
        orderRepository.save(order);
        response = paymentResponse.getPaymentStatus().equals("success") ? "payment processing successful and order placed" : "there is failure in payment";
        return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);

    }

    public List<Languages> getAllLanguages() {
        return (List<Languages>) languageRepository.findAll();
    }
}
