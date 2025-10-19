package com.Voyago_Travel.Voyago_Travel.service.impl;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Voyago_Travel.Voyago_Travel.dao.UserDao;
import com.Voyago_Travel.Voyago_Travel.dto.EmailDto;
import com.Voyago_Travel.Voyago_Travel.dto.LoginDto;
import com.Voyago_Travel.Voyago_Travel.dto.OtpDto;
import com.Voyago_Travel.Voyago_Travel.dto.PasswordDto;
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

	@Override
	public ResponseDto resendOtp(EmailDto emailDto) {
		User user = userDao.findByEmail(emailDto.getEmail());
		int otp = new Random().nextInt(100000,1000000);
		emailSender.sendOtp(user.getEmail(), otp,user.getName());
		user.setOtp(otp);
		user.setOtpExpirytime(LocalDateTime.now().plusMinutes(5));
		userDao.saveUser(user);
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", emailDto.getEmail());
		return new ResponseDto("Otp Resent success vlid only for % minutes", map);
	}

	@Override
	public ResponseDto forgotPassword(PasswordDto passwordDto) throws TimeoutException {
		User user = userDao.findByEmail(passwordDto.getEmail());
		if(LocalDateTime.now().isBefore(user.getOtpExpirytime())) {
			if(passwordDto.getOtp() == user.getOtp()) {
				user.setPassword(encoder.encode(passwordDto.getPassword()));
				user.setOtp(0);
				user.setOtpExpirytime(null);
				userDao.saveUser(user);
				return new ResponseDto("Password Updated success",user);
			}else {
				throw new InputMismatchException("Otp miss match, Try Again");
				
			}
		}else {
			throw new TimeoutException("Otp Expired, Resend Otp and Try Again");
		}
	}

	@Override
	public ResponseDto forgotPassword(EmailDto emailDto) throws TimeoutException{
		User user = userDao.findByEmail(emailDto.getEmail());
		int otp = new Random().nextInt(100000,1000000);
		emailSender.sendForgotOtp(user.getEmail(),otp,user.getName());
		user.setOtp(otp);
		user.setOtpExpirytime(LocalDateTime.now().plusMinutes(5));
		Map<String,String> map = new HashMap<String,String>();
		map.put("email", emailDto.getEmail());
		return new ResponseDto("Otp sent success valid for 5 minutes", map);
	}

	@Override
	public ResponseDto login(LoginDto loginDto) {
		return new ResponseDto("Login success", loginDto);
	}

	
	}
