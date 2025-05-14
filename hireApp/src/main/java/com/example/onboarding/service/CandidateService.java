package com.example.onboarding.service;

import com.example.onboarding.entity.Candidate;
import com.example.onboarding.entity.enums.CandidateStatus;
import com.example.onboarding.entity.enums.OnboardStatus;
import com.example.onboarding.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;

    // Add a new candidate
    public Candidate addCandidate(Candidate candidate) {
        String email = getLoggedInEmail(); // Get logged-in user's email

        if (candidateRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Candidate with this email already exists");
        }

        candidate.setEmail(email); // Enforce token-based identity
        candidate.setStatus(CandidateStatus.APPLIED); // Default status
        candidate.setOnboardStatus(OnboardStatus.NOT_STARTED); // Default onboard status
        candidate.setCreatedAt(LocalDateTime.now()); // Set created timestamp
        candidate.setUpdatedAt(LocalDateTime.now()); // Set updated timestamp

        return candidateRepository.save(candidate); // Save candidate to database
    }

    public Candidate updateCandidateDetails(Candidate updatedData) {
        String email = getLoggedInEmail(); // Get logged-in user's email
        Candidate existing = candidateRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        existing.setFirstName(updatedData.getFirstName());
        existing.setLastName(updatedData.getLastName());
        existing.setPhoneNumber(updatedData.getPhoneNumber());
        existing.setUpdatedAt(LocalDateTime.now());

        return candidateRepository.save(existing);
    }

    // Helper method to extract the logged-in user's email from the token
    private String getLoggedInEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            return userDetails.getUsername(); // Username is assumed to be email
        } else if (principal instanceof String str) {
            return str; // Fallback (e.g., anonymous user, though unlikely in this case)
        } else {
            throw new RuntimeException("Unable to extract email from token");
        }
    }
}
