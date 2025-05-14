package com.example.onboarding.controller;

import com.example.onboarding.dto.BankInfoDTO;
import com.example.onboarding.dto.EducationInfoDTO;
import com.example.onboarding.dto.PersonalInfoDTO;
import com.example.onboarding.service.CandidateInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateInfoController {

    private final CandidateInfoService infoService;

    @PutMapping("/{id}/personal-info")
    public ResponseEntity<String> updatePersonalInfo(@PathVariable Long id,
                                                     @RequestBody PersonalInfoDTO dto) {
        infoService.updatePersonalInfo(id, dto);
        return ResponseEntity.ok("Personal info updated");
    }

    @PutMapping("/{id}/bank-info")
    public ResponseEntity<String> updateBankInfo(@PathVariable Long id,
                                                 @RequestBody BankInfoDTO dto) {
        infoService.updateBankInfo(id, dto);
        return ResponseEntity.ok("Bank info updated");
    }

    @PutMapping("/{id}/educational-info")
    public ResponseEntity<String> updateEducationalInfo(@PathVariable Long id,
                                                        @RequestBody EducationInfoDTO dto) {
        infoService.updateEducationInfo(id, dto);
        return ResponseEntity.ok("Educational info updated");
    }
}

