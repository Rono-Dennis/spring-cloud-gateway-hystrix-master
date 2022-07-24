    package com.example.orderservice.service;

    import com.example.orderservice.common.Payment;
    import com.example.orderservice.common.TransactionRequest;
    import com.example.orderservice.common.TransactionResponse;
    import com.example.orderservice.entity.Order;
    import com.example.orderservice.repository.OrderRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.web.client.RestTemplate;

    @Service
    public class OrderService {
        @Autowired
        private RestTemplate restTemplate;

        @Autowired
    private OrderRepository orderRepository;

        String response="";
        public TransactionResponse saveOrder(TransactionRequest transactionRequest){
            Order order = transactionRequest.getOrder();
            Payment payment = transactionRequest.getPayment();
            payment.setOrderId(order.getId());
            payment.setAmount(order.getPrice());
           //rest call
           Payment paymentResponse  = restTemplate.postForObject("http://localhost:9192/payment/doPayment",payment, Payment.class);
            orderRepository.save(order);
            response=paymentResponse.getPaymentStatus().equals("success")?"payment processing successful and order placed":"there is failure in payment";
            return new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionId(),response);

        }
    }
