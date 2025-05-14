package com.example.onboarding.service;

import com.example.onboarding.dto.OtpVerificationRequest;
import com.example.onboarding.dto.RegisterRequest;
import com.example.onboarding.entity.Candidate;
import com.example.onboarding.entity.User;
import com.example.onboarding.entity.enums.Role;
import com.example.onboarding.repository.CandidateRepository;
import com.example.onboarding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JavaMailSender mailSender;

    public String register(RegisterRequest request) {
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);

        User user = userRepository.findByEmail(request.getEmail())
                .orElse(new User());

        user.setEmail(request.getEmail());
        user.setOtp(otp);
        user.setOtpGeneratedAt(LocalDateTime.now());
        user.setVerified(false);
        user.setRole(Role.CANDIDATE);

        userRepository.save(user);

        sendOtpEmail(user.getEmail(), otp);

        return "OTP sent to email.";
    }

    private void sendOtpEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP is: " + otp);
        mailSender.send(message);
    }


    @Autowired
    private CandidateRepository candidateRepository;

    public String verifyOtp(OtpVerificationRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getOtp().equals(request.getOtp()) &&
                user.getOtpGeneratedAt().isAfter(LocalDateTime.now().minusMinutes(10))) {

            user.setVerified(true);
            userRepository.save(user);

            if (!candidateRepository.findByEmail(user.getEmail()).isPresent()) {
                Candidate candidate = new Candidate();
                candidate.setEmail(user.getEmail());
                candidate.setFirstName("New");  // default, update later
                candidate.setLastName("Candidate");
                candidate.setPhoneNumber("N/A");
                candidateRepository.save(candidate);
            }

            return "OTP verified. Candidate profile created.";
        }

        throw new RuntimeException("Invalid or expired OTP");
    }

}

