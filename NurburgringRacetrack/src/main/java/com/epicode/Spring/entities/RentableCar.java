package com.epicode.Spring.entities;

import org.springframework.web.multipart.MultipartFile;

import com.epicode.Spring.enumerators.CarBody;
import com.epicode.Spring.enumerators.DriveType;
import com.epicode.Spring.enumerators.FuelType;
import com.epicode.Spring.enumerators.GearboxType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "rentable_cars")
public class RentableCar{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String brand;
	
	private String model;
	
	private Double price;
	
	private int startingProductionYear;
	
	@Enumerated
	private CarBody carBody;
	
	private int doors;
	
	private int seats;
	
	private int trunkCapacity;
	
	private Double weight;
	
	private Double length;
	
	private Double width;
	
	private Double height;
	
	private Double wheelDistance;
	
	private String engine;
	
	private int displacement;
	
	@Enumerated
	private FuelType fuel;
	
	private String maxPowerEngineSpeed;
	
	private int maxTorque;
	
	@Enumerated
	private DriveType driveType;
	
	@Enumerated
	private GearboxType gearbox;
	
	private int gears;
	
	private int topSpeed;
	
	private Double northToSixty;
	
	 @Column(length = 1024)
	private String carImgPath;

	private boolean drivable;
}
