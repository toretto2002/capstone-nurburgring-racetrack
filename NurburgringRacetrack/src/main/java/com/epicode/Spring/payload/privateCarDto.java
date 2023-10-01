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
public class privateCarDto {

	private String licencePlate;
	
	private String year;
	
	private String brand;
	
	private String model;
	
	private String displacement;
	
	private String fuel;
}
