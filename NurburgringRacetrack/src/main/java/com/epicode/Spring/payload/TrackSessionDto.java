package com.epicode.Spring.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TrackSessionDto {

	private String dateTime;
	private String driverId;
	private String rentableCarId;
	private privateCarDto privateCar;
	private String sessionPrice;
	private String experienceLevel;
	private String duration;
	private String notes;
	
}
