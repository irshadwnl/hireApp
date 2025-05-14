package com.example.onboarding.entity;

import com.example.onboarding.entity.enums.CandidateStatus;
import com.example.onboarding.entity.enums.OnboardStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "candidates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private CandidateStatus status = CandidateStatus.APPLIED;

    @Enumerated(EnumType.STRING)
    private OnboardStatus onboardStatus = OnboardStatus.NOT_STARTED;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
}
