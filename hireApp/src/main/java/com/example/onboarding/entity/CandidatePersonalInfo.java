package com.example.onboarding.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidatePersonalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Candidate candidate;

    private LocalDate dob;
    private String gender;
    private String address;
    private String nationality;
}
