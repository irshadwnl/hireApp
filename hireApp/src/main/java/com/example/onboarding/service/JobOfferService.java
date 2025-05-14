package com.example.onboarding.service;

import com.example.onboarding.config.RabbitMQConfig;
import com.example.onboarding.dto.JobOfferMessage;
import com.example.onboarding.entity.Candidate;
import com.example.onboarding.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobOfferService {

    private final RabbitTemplate rabbitTemplate;
    private final CandidateRepository candidateRepository;

    public String sendJobOfferNotification(Long candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        JobOfferMessage message = new JobOfferMessage(
                candidate.getId(),
                candidate.getEmail(),
                candidate.getFirstName() + " " + candidate.getLastName()
        );

        rabbitTemplate.convertAndSend(RabbitMQConfig.JOB_OFFER_QUEUE, message);
        return "Job offer notification queued for: " + candidate.getEmail();
    }
}

