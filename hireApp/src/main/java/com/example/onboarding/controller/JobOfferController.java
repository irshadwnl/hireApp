package com.example.onboarding.controller;

import com.example.onboarding.service.JobOfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class JobOfferController {

    private final JobOfferService jobOfferService;

    @PostMapping("/{id}/notify-job-offer")
    public ResponseEntity<String> notifyJobOffer(@PathVariable Long id) {
        return ResponseEntity.ok(jobOfferService.sendJobOfferNotification(id));
    }
}

