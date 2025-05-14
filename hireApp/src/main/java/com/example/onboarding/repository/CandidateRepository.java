package com.example.onboarding.repository;

import com.example.onboarding.entity.Candidate;
import com.example.onboarding.entity.enums.CandidateStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Optional<Candidate> findByEmail(String email);
    List<Candidate> findByStatus(CandidateStatus status);
    boolean existsByEmail(String email);
}

