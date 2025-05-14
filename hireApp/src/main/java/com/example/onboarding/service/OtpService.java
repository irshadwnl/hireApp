package com.example.onboarding.service;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {
    private final Map<String, String> otpStorage = new HashMap<>();
    private final Random random = new Random();

    public String generateOtp(String email) {
        String otp = String.format("%06d", random.nextInt(999999));
        otpStorage.put(email, otp);
        // Log it (simulate email send)
        System.out.println("OTP for " + email + " is: " + otp);
        return otp;
    }

    public boolean validateOtp(String email, String otp) {
        String storedOtp = otpStorage.get(email);
        return storedOtp != null && storedOtp.equals(otp);
    }

    public void removeOtp(String email) {
        otpStorage.remove(email);
    }
}

