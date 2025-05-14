package com.example.onboarding.entity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Candidate candidate;

    private String documentType; // e.g., "Resume", "ID Proof"
    private String fileUrl;      // File path or cloud URL
    private boolean verified;
}

