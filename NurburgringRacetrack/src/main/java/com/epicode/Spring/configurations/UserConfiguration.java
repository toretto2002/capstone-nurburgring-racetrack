package com.epicode.Spring.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.epicode.Spring.security.entity.User;

@Configuration
public class UserConfiguration {
	
	@Bean("userBean")
	@Scope("prototype")
	public User userBean() {
		return new User();
	}

}
