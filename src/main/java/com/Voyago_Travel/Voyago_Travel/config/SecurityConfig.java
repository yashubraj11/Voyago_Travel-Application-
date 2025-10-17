package com.Voyago_Travel.Voyago_Travel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
@Bean
SecurityFilterChain chain(HttpSecurity http)throws Exception{
	return http.csrf(x -> x.disable()).authorizeHttpRequests(req ->req.requestMatchers("/api/v1/user/auth/**").permitAll()).build();
}
@Bean
PasswordEncoder encoder() {
	return new BCryptPasswordEncoder();
}
}
