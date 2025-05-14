package com.example.onboarding.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateBankInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Candidate candidate;

    private String bankName;
    private String accountNumber;
    private String ifscCode;
}
