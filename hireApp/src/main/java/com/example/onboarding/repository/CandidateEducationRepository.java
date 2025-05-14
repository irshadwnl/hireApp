package com.example.onboarding.repository;

import com.example.onboarding.entity.CandidateEducation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateEducationRepository extends JpaRepository<CandidateEducation, Long> {
    Optional<CandidateEducation> findByCandidateId(Long candidateId);
}
