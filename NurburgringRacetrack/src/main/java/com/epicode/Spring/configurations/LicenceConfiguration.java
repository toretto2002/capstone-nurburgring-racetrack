package com.epicode.Spring.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.epicode.Spring.entities.Licence;

@Configuration
public class LicenceConfiguration {

	@Bean("licenceBean")
	@Scope("prototype")
	public Licence licenceBean() {
		return new Licence();
	}
}
