package com.epicode.Spring.entities;

import com.epicode.Spring.enumerators.FuelType;

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
@Table(name = "private_cars")
public class PrivateCar{

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 private String licencePlate;
	 
	 private int year;
	 
	 private String brand;
	 
	 private String model;
	 
	 private String displacement;
	 
	 @Enumerated
	 private FuelType fuel;
}
