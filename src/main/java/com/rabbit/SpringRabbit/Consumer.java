package com.rabbit.SpringRabbit;

import org.springframework.amqp.ImmediateAcknowledgeAmqpException;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.Optional;

@Configuration
public class Consumer {

    private final StreamBridge streamBridge;

    public Consumer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @Bean
public java.util.function.Consumer<Message<OrderCreateEvent>> orderConsumer(){
    return message -> {
        var retryCountNullable = message.getHeaders().get("amqp_retryCount", Long.class);

        var retryCount = Optional.ofNullable(retryCountNullable).orElse(5L);

        if (retryCount < 5){
            throw new RuntimeException("Erro de retry");
        }

        if (retryCount >= 5){
            streamBridge.send("final-dlq-order-out-0", message.getPayload());
            throw new ImmediateAcknowledgeAmqpException("enviado para a dlq final");
        }
    };

    }
    @Bean
public java.util.function.Consumer<Message<OrderCreateEvent>> orderConsumerB(){
    return message -> System.out.println("Recebido B: " + message.getPayload().getOrderId());

    }
}
