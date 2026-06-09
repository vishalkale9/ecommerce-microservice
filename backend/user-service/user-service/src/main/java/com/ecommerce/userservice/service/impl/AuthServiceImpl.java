package com.ecommerce.userservice.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.userservice.dto.SignupRequest;
import com.ecommerce.userservice.dto.UserResponse;
import com.ecommerce.userservice.entity.Role;
import com.ecommerce.userservice.entity.User;
import com.ecommerce.userservice.repository.UserRepository;
import com.ecommerce.userservice.service.AuthService;

import com.ecommerce.userservice.dto.JwtAuthResponse;
import com.ecommerce.userservice.dto.LoginRequest;
import com.ecommerce.userservice.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;


	@Override
	public UserResponse registerUser(SignupRequest request) {
		
	
		//check if email exists or not
		
		if(userRepository.existsByEmail(request.getEmail()))
		 {
			 throw new RuntimeException("emails is alredy used");
		 }
		 
		 //build the user entity
		   User user = User.builder()
		               .email(request.getEmail())
		               .password(passwordEncoder.encode(request.getPassword()))
		               .role(Role.CUSTOMER)
		               .build();
		          
		 // save to db  
		  User savedUser = userRepository.save(user);
		  		          
		 //return userResponse        
		return UserResponse.builder()
				.id(savedUser.getId())
				.email(savedUser.getEmail())
				.role(savedUser.getRole().name())
				.createdAt(savedUser.getCreatedAt())
				.build();
	}
	@Override
	public JwtAuthResponse login(LoginRequest request) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtTokenProvider.generateToken(authentication);
		
		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found"));
				
		UserResponse userResponse = UserResponse.builder()
				.id(user.getId())
				.email(user.getEmail())
				.role(user.getRole().name())
				.createdAt(user.getCreatedAt())
				.build();
				
		return JwtAuthResponse.builder()
				.accessToken(token)
				.user(userResponse)
				.build();
	}

}
