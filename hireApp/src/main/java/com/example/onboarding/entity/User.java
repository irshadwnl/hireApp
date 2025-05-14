package com.example.onboarding.entity;

import com.example.onboarding.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String otp;

    private boolean verified;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime otpGeneratedAt;
}
