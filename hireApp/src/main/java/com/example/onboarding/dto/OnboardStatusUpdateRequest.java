package com.example.onboarding.dto;

import lombok.Data;

@Data
public class OnboardStatusUpdateRequest {
    private String onboardStatus;  // should be one of the enums
}
