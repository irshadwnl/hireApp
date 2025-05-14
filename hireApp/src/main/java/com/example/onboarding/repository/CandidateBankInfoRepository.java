package com.example.onboarding.repository;

import com.example.onboarding.entity.CandidateBankInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateBankInfoRepository extends JpaRepository<CandidateBankInfo, Long> {
    Optional<CandidateBankInfo> findByCandidateId(Long candidateId);
}

