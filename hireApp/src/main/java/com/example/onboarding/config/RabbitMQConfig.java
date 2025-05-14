package com.example.onboarding.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;


@Configuration
public class RabbitMQConfig {

    public static final String JOB_OFFER_QUEUE = "job_offer_queue";

    @Bean
    public Queue jobOfferQueue() {
        return new Queue(JOB_OFFER_QUEUE, true);
    }
}

