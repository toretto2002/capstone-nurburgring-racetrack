package com.epicode.Spring.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.epicode.Spring.entities.TrackSession;

@Configuration
public class TrackSessionConfiguration {

	@Bean("trackSessionBean")
	@Scope("prototype")
	public TrackSession trackSessionBeans() {
		return new TrackSession();
	}
}
