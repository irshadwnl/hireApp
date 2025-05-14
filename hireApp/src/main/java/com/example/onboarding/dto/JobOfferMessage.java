package com.example.onboarding.dto;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferMessage {
    private Long candidateId;
    private String email;
    private String fullName;
}

