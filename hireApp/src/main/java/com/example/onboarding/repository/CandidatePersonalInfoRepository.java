package com.example.onboarding.repository;

import com.example.onboarding.entity.CandidatePersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidatePersonalInfoRepository extends JpaRepository<CandidatePersonalInfo, Long> {
    Optional<CandidatePersonalInfo> findByCandidateId(Long candidateId);
}
