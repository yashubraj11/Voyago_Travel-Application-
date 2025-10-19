package com.Voyago_Travel.Voyago_Travel.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailDto {
	
	@NotBlank(message="Email is required")
	@Email(message="Invalid email format")
private String email;
}
