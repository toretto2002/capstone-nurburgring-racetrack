package com.epicode.Spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.epicode.Spring.entities.RentableCar;
import com.epicode.Spring.enumerators.CarBody;
import com.epicode.Spring.enumerators.DriveType;
import com.epicode.Spring.enumerators.FuelType;
import com.epicode.Spring.enumerators.GearboxType;
import com.epicode.Spring.payload.RentableCarDto;
import com.epicode.Spring.repositories.RentableCarRepository;

@Service
public class BackofficeService {

	@Autowired@Qualifier("rentableCarBean") private ObjectProvider<RentableCar> rentableCarProvider;
	@Autowired RentableCarRepository rentableCarRepo;
	
	public RentableCar addCar(RentableCarDto rentableCarDto){
		
		RentableCar rentCar = rentableCarProvider.getObject();
		
		rentCar.setBrand(rentableCarDto.getBrand());
		rentCar.setModel(rentableCarDto.getModel());
		try {
			rentCar.setPrice(Double.parseDouble(rentableCarDto.getPrice()));
		}catch(NumberFormatException e) {
			throw new NumberFormatException("Couldn't parse the car price into a double");
		}
		try {
			rentCar.setStartingProductionYear(Integer.parseInt(rentableCarDto.getStartingProductionYear()));
		}catch(NumberFormatException e) {
			throw new NumberFormatException("Couldn't parse the car year into an integer");
		}
		try {
			CarBody carBody = CarBody.valueOf(rentableCarDto.getCarBody());
			rentCar.setCarBody(carBody);
		}catch(IllegalArgumentException e) {
			throw new IllegalArgumentException("Error in the car body type!!");
		}
		try {
			rentCar.setDoors(Integer.parseInt(rentableCarDto.getDoors()));
		}catch(NumberFormatException e) {
			throw new NumberFormatException("Couldn't parse the car doors number into an integer");
		}
		try {
			rentCar.setSeats(Integer.parseInt(rentableCarDto.getSeats()));
		}catch(NumberFormatException e) {
			throw new NumberFormatException("Couldn't parse the car seats number into an integer");
		}
		try {
			rentCar.setTrunkCapacity(Integer.parseInt(rentableCarDto.getTrunkCapacity()));
		}catch(NumberFormatException e) {
			throw new NumberFormatException("Couldn't parse the car trunk capacity into an integer");
		}
		try {
			rentCar.setWeight(Double.parseDouble(rentableCarDto.getWeight()));
		}catch(NumberFormatException e) {
			throw new NumberFormatException("Couldn't parse the car weight into a double");
		}
		try {
			rentCar.setLength(Double.parseDouble(rentableCarDto.getLength()));
		}catch(NumberFormatException e) {
			throw new NumberFormatException("Couldn't parse the car length into a double");
		}
		try {
			rentCar.setHeight(Double.parseDouble(rentableCarDto.getHeight()));
		}catch(NumberFormatException e) {
			throw new NumberFormatException("Couldn't parse the car height into a double");
		}
		try {
			rentCar.setWidth(Double.parseDouble(rentableCarDto.getWidth()));
		}catch(NumberFormatException e) {
			throw new NumberFormatException("Couldn't parse the car width into a double");
		}
		try {
			rentCar.setWheelDistance(Double.parseDouble(rentableCarDto.getWheelDistance()));
		}catch(NumberFormatException e) {
			throw new NumberFormatException("Couldn't parse the car wheel distance into a double");
		}
		
		rentCar.setEngine(rentableCarDto.getEngine());
		
		try {
			FuelType fuelType = FuelType.valueOf(rentableCarDto.getFuel());
			rentCar.setFuel(fuelType);
		}catch(IllegalArgumentException e) {
			throw new IllegalArgumentException("Error in the Fuel type!!");
		}
		
		rentCar.setMaxPowerEngineSpeed(rentableCarDto.getMaxPowerEngineSpeed());
		
		try {
			rentCar.setMaxTorque(Integer.parseInt(rentableCarDto.getMaxTorque()));
		}catch(NumberFormatException e) {
			throw new NumberFormatException("Couldn't parse the torque number into an integer");
		}
		
		try {
			DriveType driveType = DriveType.valueOf(rentableCarDto.getDriveType());
			rentCar.setDriveType(driveType);
		}catch(IllegalArgumentException e) {
			throw new IllegalArgumentException("Error in the Drive type!!");
		}
		
		try {
			GearboxType gearbox = GearboxType.valueOf(rentableCarDto.getGearbox());
			rentCar.setGearbox(gearbox);
		}catch(IllegalArgumentException e) {
			throw new IllegalArgumentException("Error in the Drive type!!");
		}
		
		try {
			rentCar.setGears(Integer.parseInt(rentableCarDto.getGears()));
		}catch(NumberFormatException e) {
			throw new NumberFormatException("Couldn't parse the gears number into an integer");
		}
		
		try {
			rentCar.setTopSpeed(Integer.parseInt(rentableCarDto.getTopSpeed()));
		}catch(NumberFormatException e) {
			throw new NumberFormatException("Couldn't parse the top speed number into an integer");
		}
		
		rentCar.setCarImgPath(rentableCarDto.getCarImgPath());
		
		try {
			rentCar.setNorthToSixty(Double.parseDouble(rentableCarDto.getNorthToSixty()));
		}catch(NumberFormatException e) {
			throw new NumberFormatException("Couldn't parse the north to sixty time into a double");
		}
		
		rentCar.setDrivable(true);
		
		rentableCarRepo.save(rentCar);
		
		return rentCar;
	}

	public List<RentableCar> getAllCar(){
		return this.rentableCarRepo.findAll();
	}
	
	public Optional<RentableCar> getById(Long id) {
		return this.rentableCarRepo.findById(id);
	}
	
	public RentableCar updateCar(Long id, RentableCar car) {
		return this.rentableCarRepo.save(car);
	}
	
	public void deleteCarById(Long id) {
		RentableCar car = this.rentableCarRepo.findById(id).get();
		this.rentableCarRepo.delete(car);
	}
}
