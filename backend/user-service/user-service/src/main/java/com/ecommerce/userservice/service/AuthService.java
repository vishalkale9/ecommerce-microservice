package com.ecommerce.userservice.service;

import com.ecommerce.userservice.dto.SignupRequest;
import com.ecommerce.userservice.dto.UserResponse;
import com.ecommerce.userservice.dto.JwtAuthResponse;
import com.ecommerce.userservice.dto.LoginRequest;

public interface AuthService {

	UserResponse registerUser(SignupRequest signupRequest);
	
	JwtAuthResponse login(LoginRequest loginRequest);
}
