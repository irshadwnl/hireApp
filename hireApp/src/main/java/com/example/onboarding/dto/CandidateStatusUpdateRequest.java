package com.example.onboarding.dto;

import lombok.Data;

@Data
public class CandidateStatusUpdateRequest {
    private String status;  // should be one of the enums
}
