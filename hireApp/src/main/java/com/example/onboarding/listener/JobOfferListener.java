package com.example.onboarding.listener;

import com.example.onboarding.config.RabbitMQConfig;
import com.example.onboarding.dto.JobOfferMessage;
import com.example.onboarding.entity.Candidate;
import com.example.onboarding.entity.JobOfferNotification;
import com.example.onboarding.repository.CandidateRepository;
import com.example.onboarding.repository.JobOfferNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JobOfferListener {

    private final JavaMailSender mailSender;
    private final CandidateRepository candidateRepository;
    private final JobOfferNotificationRepository notificationRepository;

    @RabbitListener(queues = RabbitMQConfig.JOB_OFFER_QUEUE)
    public void processJobOffer(JobOfferMessage message) {
        Optional<Candidate> candidateOpt = candidateRepository.findById(message.getCandidateId());
        if (candidateOpt.isEmpty()) return;

        Candidate candidate = candidateOpt.get();
        JobOfferNotification notification = new JobOfferNotification();
        notification.setCandidate(candidate);
        notification.setSentAt(LocalDateTime.now());

        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(candidate.getEmail());
            email.setSubject("🎉 Job Offer from Our Company");
            email.setText("Hi " + message.getFullName() + ",\n\nYou have been selected for the role. Congratulations!");

            mailSender.send(email);

            notification.setSent(true);
            notification.setRetryCount(0);
        } catch (Exception ex) {
            notification.setSent(false);
            notification.setRetryCount(1);
            notification.setErrorMessage(ex.getMessage());
        }

        notificationRepository.save(notification);
    }
}

