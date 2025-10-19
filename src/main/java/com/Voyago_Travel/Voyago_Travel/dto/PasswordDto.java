package com.Voyago_Travel.Voyago_Travel.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PasswordDto {
	@NotEmpty(message="Email is Required")
	@Email(message = "Email should be proper")
private String email;
	@Pattern(regexp = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "Password should contain one uppercase lowercase special character and number and min 8 digits")
private String password;
private int otp;
}
