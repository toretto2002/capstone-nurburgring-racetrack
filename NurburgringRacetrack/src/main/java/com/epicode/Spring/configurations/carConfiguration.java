package com.epicode.Spring.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.epicode.Spring.entities.PrivateCar;
import com.epicode.Spring.entities.RentableCar;

@Configuration
public class carConfiguration {

	@Bean("privateCarBean")
	@Scope("prototype")
	public PrivateCar privateCarBean() {
		return new PrivateCar();
	}
	
	@Bean("rentableCarBean")
	@Scope("prototype")
	public RentableCar rentableCarBean() {
		return new RentableCar();
	}
	
}
