package com.Voyago_Travel.Voyago_Travel.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;
private String email;
private Long mobile;
private String password;
private LocalDateTime createdTime;
@Enumerated(EnumType.STRING)
private Role role;
private int otp;
private LocalDateTime otpExpirytime;
private boolean status;
}
