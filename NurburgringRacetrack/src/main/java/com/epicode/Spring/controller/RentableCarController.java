package com.epicode.Spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epicode.Spring.entities.RentableCar;
import com.epicode.Spring.payload.RentableCarDto;
import com.epicode.Spring.service.BackofficeService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/backoffice")
public class RentableCarController {

	@Autowired BackofficeService backOfficeSvc;
	
	@PostMapping(value = "/rent-car")
	public ResponseEntity<RentableCar> bookSession(@RequestBody RentableCarDto rentCarDto){
		System.out.println(rentCarDto);
		RentableCar rentCar = backOfficeSvc.addCar(rentCarDto);
		return new ResponseEntity<>(rentCar , HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/rent-car")
	public ResponseEntity<List<RentableCar>> getAllRentCar() {
		List<RentableCar> allCar = backOfficeSvc.getAllCar();
		
		return new ResponseEntity<>(allCar , HttpStatus.OK);
	}
	
	@GetMapping(value = "/rent-car/{id}")
	public ResponseEntity<RentableCar> getById(@PathVariable Long id) throws Exception{
		
		Optional<RentableCar> optionable = backOfficeSvc.getById(id);
		
		if(optionable.isPresent()) {
			RentableCar car = optionable.get();
			return new ResponseEntity<>(car , HttpStatus.OK);
		}else {
			throw new Exception("Auto non trovata");
		}
	}
	
	@PutMapping(value = "/rent-car/{id}")
	public ResponseEntity<RentableCar> updateCar(@PathVariable Long id ,@RequestBody RentableCar rentCarDto){
		System.out.println(rentCarDto);
		RentableCar car = backOfficeSvc.updateCar(id, rentCarDto);
		return new ResponseEntity<>(car , HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/rent-car/{id}")
	public ResponseEntity<?> deleteCar(@PathVariable Long id ){
		backOfficeSvc.deleteCarById(id);
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
}
