package com.Voyago_Travel.Voyago_Travel.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	@Size(min=3, max=30, message = "Name should be between 3~30 characters")
private String name;
	@NotEmpty(message="Email is Required")
	@Email(message="Emai should be proper")
private String email;
	@Pattern(regexp = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "Password should contain one uppercase lowercase special character and number and min 8 digits")
private String password;
	@DecimalMin(value="6000000000", message = "Enter proper Mobile number")
	@DecimalMax(value = "9999999999", message = "enter proper Mobile Number")
private Long mobile;
	@NotEmpty
private String role;


}
