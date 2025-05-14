package com.example.onboarding.controller;

import com.example.onboarding.dto.CandidateStatusUpdateRequest;
import com.example.onboarding.dto.OnboardStatusUpdateRequest;
import com.example.onboarding.entity.Candidate;
import com.example.onboarding.entity.enums.CandidateStatus;
import com.example.onboarding.entity.enums.OnboardStatus;
import com.example.onboarding.repository.CandidateRepository;
import com.example.onboarding.service.CandidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateRepository candidateRepository;
    private final CandidateService candidateService;

    @GetMapping("/hired")
    public List<Candidate> getHiredCandidates() {
        return candidateRepository.findByStatus(CandidateStatus.OFFERED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidate(@PathVariable Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));
        return ResponseEntity.ok(candidate);
    }

    @GetMapping("/count")
    public Map<String, Long> countCandidates() {
        long count = candidateRepository.count();
        return Collections.singletonMap("totalCandidates", count);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateCandidateStatus(@PathVariable Long id,
                                                        @RequestBody CandidateStatusUpdateRequest request) {

        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        try {
            CandidateStatus newStatus = CandidateStatus.valueOf(request.getStatus().toUpperCase());
            candidate.setStatus(newStatus);
            candidate.setUpdatedAt(LocalDateTime.now());
            candidateRepository.save(candidate);
            return ResponseEntity.ok("Candidate status updated to " + newStatus);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status: " + request.getStatus());
        }
    }

    @PutMapping("/{id}/onboard-status")
    public ResponseEntity<String> updateOnboardStatus(@PathVariable Long id,
                                                      @RequestBody OnboardStatusUpdateRequest request) {

        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        try {
            OnboardStatus newStatus = OnboardStatus.valueOf(request.getOnboardStatus().toUpperCase());
            candidate.setOnboardStatus(newStatus);
            candidate.setUpdatedAt(LocalDateTime.now());
            candidateRepository.save(candidate);
            return ResponseEntity.ok("Candidate onboard status updated to " + newStatus);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid onboard status: " + request.getOnboardStatus());
        }
    }
    @PostMapping
    public Candidate addCandidate(@RequestBody Candidate candidate) {
        return candidateService.addCandidate(candidate);
    }
    @PutMapping
    public ResponseEntity<?> updateCandidate(@RequestBody Candidate updatedData) {
        Candidate existing = candidateService.updateCandidateDetails(updatedData); // Using the service method

        return ResponseEntity.ok(existing);
    }


}
