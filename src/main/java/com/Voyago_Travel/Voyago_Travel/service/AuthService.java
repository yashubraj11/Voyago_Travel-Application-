package com.Voyago_Travel.Voyago_Travel.service;

import java.util.concurrent.TimeoutException;

import com.Voyago_Travel.Voyago_Travel.dto.EmailDto;
import com.Voyago_Travel.Voyago_Travel.dto.LoginDto;
import com.Voyago_Travel.Voyago_Travel.dto.OtpDto;
import com.Voyago_Travel.Voyago_Travel.dto.PasswordDto;
import com.Voyago_Travel.Voyago_Travel.dto.ResponseDto;
import com.Voyago_Travel.Voyago_Travel.dto.UserDto;

public interface AuthService {
ResponseDto register(UserDto userDto);


ResponseDto verifyOtp(OtpDto otpDto) throws TimeoutException;

ResponseDto resendOtp(EmailDto emailDto);

ResponseDto forgotPassword(PasswordDto passwordDto) throws TimeoutException;

ResponseDto forgotPassword(EmailDto emailDto) throws TimeoutException;

ResponseDto login (LoginDto loginDto);

}
