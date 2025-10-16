package com.Voyago_Travel.Voyago_Travel.dao;

import org.springframework.stereotype.Repository;

import com.Voyago_Travel.Voyago_Travel.Repository.UserRepository;
import com.Voyago_Travel.Voyago_Travel.entity.User;
import com.Voyago_Travel.Voyago_Travel.exception.DataNotFoundException;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class UserDao {
UserRepository userRepository;
public User saveUser(User user) {
	return userRepository.save(user);
	
}

public boolean isEmailUnique(String email) {
	return !userRepository.existsByEmail(email);
}

public boolean isMobileUnique(Long mobile) {
	return !userRepository.existsByMobile(mobile);
}

public User findByEmail(String email) {
	return userRepository.findByEmail(email).orElseThrow(()->new DataNotFoundException("Email Doesnot Exist"));
}
}
