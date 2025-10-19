package com.Voyago_Travel.Voyago_Travel.controller;

import java.util.concurrent.TimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Voyago_Travel.Voyago_Travel.dto.EmailDto;
import com.Voyago_Travel.Voyago_Travel.dto.LoginDto;
import com.Voyago_Travel.Voyago_Travel.dto.OtpDto;
import com.Voyago_Travel.Voyago_Travel.dto.PasswordDto;
import com.Voyago_Travel.Voyago_Travel.dto.ResponseDto;
import com.Voyago_Travel.Voyago_Travel.dto.UserDto;
import com.Voyago_Travel.Voyago_Travel.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user/auth")
public class AuthController {
AuthService authService;
	
	@PostMapping("/register")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseDto register(@Valid @RequestBody UserDto userDto) {
		return authService.register(userDto);
	}
	
	@PostMapping("/verify-otp")
	@ResponseStatus(code =HttpStatus.CREATED)
	public ResponseDto verifyOtp(@RequestBody OtpDto otpDto) throws TimeoutException{
		return authService.verifyOtp(otpDto);
	}
	
	@PostMapping("/resend-otp")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseDto resendOtp(@RequestBody EmailDto emailDto) {
		return authService.resendOtp(emailDto);
	}
	@PostMapping("/forgot-password/req")
	@ResponseStatus(code=HttpStatus.OK)
	public ResponseDto forgotPassword(@RequestBody EmailDto emailDto) throws TimeoutException {
		return authService.forgotPassword(emailDto);
	}
	
	@PostMapping("/forgot-password/reset")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseDto forgotPassword(@Valid @RequestBody PasswordDto passwordDto )throws TimeoutException{
		return authService.forgotPassword(passwordDto);
	}
	@PostMapping("/login")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseDto login(@Valid @RequestBody LoginDto loginDto) {
		return authService.login(loginDto);
	}
}
