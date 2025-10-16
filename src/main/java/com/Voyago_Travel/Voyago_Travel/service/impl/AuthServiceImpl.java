package com.Voyago_Travel.Voyago_Travel.service.impl;


import java.time.LocalDateTime;

import java.util.InputMismatchException;

import java.util.Random;
import java.util.concurrent.TimeoutException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Voyago_Travel.Voyago_Travel.dao.UserDao;
import com.Voyago_Travel.Voyago_Travel.dto.OtpDto;
import com.Voyago_Travel.Voyago_Travel.dto.ResponseDto;
import com.Voyago_Travel.Voyago_Travel.dto.UserDto;
import com.Voyago_Travel.Voyago_Travel.entity.Role;
import com.Voyago_Travel.Voyago_Travel.entity.User;
import com.Voyago_Travel.Voyago_Travel.exception.DataExistsException;
import com.Voyago_Travel.Voyago_Travel.service.AuthService;
import com.Voyago_Travel.Voyago_Travel.util.EmailSender;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class AuthServiceImpl implements AuthService {

    private final UserDao userDao;
    private final PasswordEncoder encoder;
    private final EmailSender emailSender;

    @Override
    public ResponseDto register(UserDto userDto) {
        if (userDao.isEmailUnique(userDto.getEmail()) && userDao.isMobileUnique(userDto.getMobile())) {
            int otp = new Random().nextInt(100000, 1000000);
            emailSender.sendOtp(userDto.getEmail(), otp, userDto.getName());

            User user = new User();
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPassword(encoder.encode(userDto.getPassword()));
            user.setMobile(userDto.getMobile());
            user.setCreatedTime(LocalDateTime.now());
            user.setOtp(otp);
            user.setOtpExpirytime(LocalDateTime.now().plusMinutes(5));
            user.setRole(Role.valueOf(userDto.getRole().toUpperCase())); 
            user.setStatus(false);

            userDao.saveUser(user);

            return new ResponseDto("OTP sent successfully. Verify within 5 minutes.", userDto);
        } else {
            if (!userDao.isEmailUnique(userDto.getEmail()))
                throw new DataExistsException("Email Already Exists: " + userDto.getEmail());
            else
                throw new DataExistsException("Mobile Already Exists: " + userDto.getMobile());
        }
    }

	@Override
	public ResponseDto verifyOtp(OtpDto otpDto) throws TimeoutException {
		User user = userDao.findByEmail(otpDto.getEmail());
		if(LocalDateTime.now().isBefore(user.getOtpExpirytime())) {
			if(otpDto.getOtp()==user.getOtp()) {
				user.setStatus(true);
				user.setOtp(0);
				user.setOtpExpirytime(null);
				userDao.saveUser(user);
				return new ResponseDto("Account Created success",user);
			}else {
				throw new InputMismatchException("otp miss match, try again");
			}
		}else {
			throw new TimeoutException("otp expired, resend otp and try again");
		}
	}

	
	}
