package com.example.onboarding.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Candidate candidate;

    private boolean sent;

    private LocalDateTime sentAt;

    private int retryCount;

    private String errorMessage;
}
