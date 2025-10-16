package com.Voyago_Travel.Voyago_Travel.service;

import java.util.concurrent.TimeoutException;
import com.Voyago_Travel.Voyago_Travel.dto.OtpDto;
import com.Voyago_Travel.Voyago_Travel.dto.ResponseDto;
import com.Voyago_Travel.Voyago_Travel.dto.UserDto;

public interface AuthService {
ResponseDto register(UserDto userDto);


ResponseDto verifyOtp(OtpDto otpDto) throws TimeoutException;




}
