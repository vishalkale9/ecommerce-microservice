package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.dto.SignupRequest;
import com.ecommerce.userservice.dto.UserResponse;
import com.ecommerce.userservice.dto.JwtAuthResponse;
import com.ecommerce.userservice.dto.LoginRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import com.ecommerce.userservice.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<UserResponse> registerUser(@Validated @RequestBody SignupRequest request) {
		UserResponse registerUser = authService.registerUser(request);
		return new ResponseEntity<>(registerUser, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> login(@Validated @RequestBody LoginRequest request) {
		JwtAuthResponse response = authService.login(request);
		return ResponseEntity.ok(response);
	}
}
