package com.example.onboarding.service;

import com.example.onboarding.dto.BankInfoDTO;
import com.example.onboarding.dto.EducationInfoDTO;
import com.example.onboarding.dto.PersonalInfoDTO;
import com.example.onboarding.entity.Candidate;
import com.example.onboarding.entity.CandidateBankInfo;
import com.example.onboarding.entity.CandidateEducation;
import com.example.onboarding.entity.CandidatePersonalInfo;
import com.example.onboarding.repository.CandidateBankInfoRepository;
import com.example.onboarding.repository.CandidateEducationRepository;
import com.example.onboarding.repository.CandidatePersonalInfoRepository;
import com.example.onboarding.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class CandidateInfoService {

    private final CandidateRepository candidateRepository;
    private final CandidatePersonalInfoRepository personalRepo;
    private final CandidateBankInfoRepository bankRepo;
    private final CandidateEducationRepository eduRepo;

    public void updatePersonalInfo(Long candidateId, PersonalInfoDTO dto) {
        Candidate candidate = getCandidate(candidateId);

        CandidatePersonalInfo info = personalRepo.findByCandidateId(candidateId)
                .orElse(new CandidatePersonalInfo());

        info.setCandidate(candidate);
        info.setDob(LocalDate.parse(dto.getDob()));
        info.setGender(dto.getGender());
        info.setAddress(dto.getAddress());
        info.setNationality(dto.getNationality());

        personalRepo.save(info);
    }

    public void updateBankInfo(Long candidateId, BankInfoDTO dto) {
        Candidate candidate = getCandidate(candidateId);

        CandidateBankInfo bankInfo = bankRepo.findByCandidateId(candidateId)
                .orElse(new CandidateBankInfo());

        bankInfo.setCandidate(candidate);
        bankInfo.setBankName(dto.getBankName());
        bankInfo.setAccountNumber(dto.getAccountNumber());
        bankInfo.setIfscCode(dto.getIfscCode());

        bankRepo.save(bankInfo);
    }

    public void updateEducationInfo(Long candidateId, EducationInfoDTO dto) {
        Candidate candidate = getCandidate(candidateId);

        CandidateEducation eduInfo = eduRepo.findByCandidateId(candidateId)
                .orElse(new CandidateEducation());

        eduInfo.setCandidate(candidate);
        eduInfo.setDegree(dto.getDegree());
        eduInfo.setInstitution(dto.getInstitution());
        eduInfo.setYearOfPassing(dto.getYearOfPassing());

        eduRepo.save(eduInfo);
    }

    private Candidate getCandidate(Long id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));
    }
}

