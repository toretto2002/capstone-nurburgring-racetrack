package com.epicode.Spring.payload;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class RentableCarDto {

	private String brand;
	private String model;
	private String price;
	private String startingProductionYear;
	private String carBody;
	private String doors;
	private String seats;
	private String trunkCapacity;
	private String weight;
	private String length;
	private String width;
	private String height;
	private String wheelDistance;
	private String engine;
	private String displacement;
	private String fuel;
	private String maxPowerEngineSpeed;
	private String maxTorque;
	private String driveType;	
	private String gearbox;	
	private String gears;	
	private String topSpeed;	
	private String northToSixty;
	private String carImgPath;
	private String drivable;
}
