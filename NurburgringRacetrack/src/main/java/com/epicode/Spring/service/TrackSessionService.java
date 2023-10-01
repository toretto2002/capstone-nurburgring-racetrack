package com.epicode.Spring.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.epicode.Spring.entities.PrivateCar;
import com.epicode.Spring.entities.RentableCar;
import com.epicode.Spring.entities.TrackSession;
import com.epicode.Spring.enumerators.FuelType;
import com.epicode.Spring.enumerators.TrackExperienceLevel;
import com.epicode.Spring.payload.TrackSessionDto;
import com.epicode.Spring.repositories.RentableCarRepository;
import com.epicode.Spring.repositories.TrackSessionRepository;
import com.epicode.Spring.security.entity.User;
import com.epicode.Spring.security.exception.MyAPIException;
import com.epicode.Spring.security.exception.ResourceNotFoundException;
import com.epicode.Spring.security.repository.UserRepository;

@Service
public class TrackSessionService {
	
	@Autowired @Qualifier("trackSessionBean") private ObjectProvider<TrackSession> trackSessionProvider;
	@Autowired@Qualifier("privateCarBean") private ObjectProvider<PrivateCar> privateCarProvider;
	@Autowired@Qualifier("rentableCarBean") private ObjectProvider<RentableCar> rentableCarProvider;
	@Autowired @Qualifier("userBean") private ObjectProvider<User> userProvider;
	@Autowired TrackSessionRepository trackSessionRepo;
	@Autowired UserRepository userRepo;
	@Autowired RentableCarRepository rentableCarRepo;
	
	public TrackSession saveTrackSessionBooking(TrackSessionDto trackSessionDto) {
		
		TrackSession trackSession = trackSessionProvider.getObject();
		
		if(trackSessionDto.getDriverId() == null || trackSessionDto.getDriverId().isEmpty()) {
			throw new MyAPIException(HttpStatus.BAD_REQUEST, "Driver Id Missing!!!");
		}
		
		int driverId;
		
		try {
			driverId = Integer.parseInt(trackSessionDto.getDriverId());
		}catch(NumberFormatException e) {
			throw new NumberFormatException("Couldn't parse the driver id string into an integer");
		}
		
		Optional<User> userOptional = userRepo.findById((long) driverId); 
		
		if(userOptional.isPresent()) {
			
			User user = userProvider.getObject();
			user = userOptional.get();
			trackSession.setDriver(user);
				
		}else {
			throw new ResourceNotFoundException("User", "id", driverId);
		}
		
		if((trackSessionDto.getRentableCarId() == null || trackSessionDto.getRentableCarId().isEmpty()) && trackSessionDto.getPrivateCar() != null) {
			
			PrivateCar privateCar = privateCarProvider.getObject();
			
			privateCar.setLicencePlate(trackSessionDto.getPrivateCar().getLicencePlate());
			
			try {
				privateCar.setYear(Integer.parseInt(trackSessionDto.getPrivateCar().getYear()));
			}catch(NumberFormatException e) {
				throw new NumberFormatException("Couldn't parse the private car year id string into an integer");
			}
			
			privateCar.setBrand(trackSessionDto.getPrivateCar().getBrand());
			privateCar.setModel(trackSessionDto.getPrivateCar().getModel());
			privateCar.setDisplacement(trackSessionDto.getPrivateCar().getDisplacement());
			
			try {
				FuelType fuelType = FuelType.valueOf(trackSessionDto.getPrivateCar().getFuel());
				privateCar.setFuel(fuelType);
			}catch(IllegalArgumentException e) {
				throw new IllegalArgumentException("Error in the Fuel type!!");
			}
			
			trackSession.setPrivateCar(privateCar);
			
		}else if(trackSessionDto.getRentableCarId() != null) {
			
			int rentCarId;
			
			try {
				rentCarId = Integer.parseInt(trackSessionDto.getRentableCarId());
			}catch(NumberFormatException e) {
				throw new NumberFormatException("Couldn't parse the rent car id string into an integer");
			}
			
			Optional<RentableCar> rentableCarOptional = rentableCarRepo.findById((long) rentCarId); 
			
			if(rentableCarOptional.isPresent()) {
				
				RentableCar rentCar = rentableCarProvider.getObject();
				rentCar = rentableCarOptional.get();
				trackSession.setRentableCar(rentCar);
					
			}else {
				throw new ResourceNotFoundException("Rent Car", "id", rentCarId);
			}
			
		}else if(trackSessionDto.getRentableCarId().isEmpty() && trackSessionDto.getPrivateCar() == null) {
			throw new MyAPIException(HttpStatus.BAD_REQUEST, "The Car is Missing!!!");
		}
		
		try {
            LocalDateTime dateTime = LocalDateTime.parse(trackSessionDto.getDateTime()); // the date needs to be like yyyy-MM-dd
            trackSession.setDateTime(dateTime);
        } catch (MyAPIException e) {
        	throw new MyAPIException(HttpStatus.BAD_REQUEST, "Error in date format track Session date Time!!!");
        } 
		
		try {
			trackSession.setSessionPrice(Double.parseDouble(trackSessionDto.getSessionPrice()));
		}catch(NumberFormatException e) {
			throw new NumberFormatException("Couldn't parse the session price into a double");
		}
		
		try {
			TrackExperienceLevel expLevel = TrackExperienceLevel.valueOf(trackSessionDto.getExperienceLevel());
			trackSession.setExperienceLevel(expLevel);
		}catch(IllegalArgumentException e) {
			throw new IllegalArgumentException("Error in the track experience level!!");
		}
		
		try {
			trackSession.setDuration(Double.parseDouble(trackSessionDto.getDuration()));
		}catch(NumberFormatException e) {
			throw new NumberFormatException("Couldn't parse the session duration into a double");
		}
		
		trackSession.setNotes(trackSessionDto.getNotes());
		trackSessionRepo.save(trackSession);
		
		System.out.println(trackSession);
		return trackSession;
	}

	public List<TrackSession> getAllBooking(){
		return this.trackSessionRepo.findAll();
	}
	
	public List<TrackSession> getByDriver(Long id){
		User u = userRepo.findById(id).get();
		List<TrackSession> l = this.trackSessionRepo.findByDriver(u);
		return l;
	}
	
	public void deleteSession(Long id) {
		TrackSession toDelete = this.trackSessionRepo.findById(id).get();
		this.trackSessionRepo.delete(toDelete);
	}
}
