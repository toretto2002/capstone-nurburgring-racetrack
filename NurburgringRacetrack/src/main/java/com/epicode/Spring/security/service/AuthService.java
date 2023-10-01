package com.epicode.Spring.security.service;

import org.springframework.http.ResponseEntity;

import com.epicode.Spring.security.payload.LoginDto;
import com.epicode.Spring.security.payload.RegisterDto;
import com.epicode.Spring.security.payload.TokenAndId;

public interface AuthService {
    
    TokenAndId login(LoginDto loginDto);
	String register(RegisterDto registerDto);
    
}
