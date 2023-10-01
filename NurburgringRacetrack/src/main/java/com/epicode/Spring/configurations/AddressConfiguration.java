package com.epicode.Spring.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.epicode.Spring.entities.Address;

@Configuration
public class AddressConfiguration {

	@Bean("addressBean")
	@Scope("prototype")
	public Address addressBean() {
		return new Address();
	}
}
