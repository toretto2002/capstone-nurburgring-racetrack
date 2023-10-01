package com.epicode.Spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epicode.Spring.entities.TrackSession;
import com.epicode.Spring.payload.TrackSessionDto;
import com.epicode.Spring.service.TrackSessionService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/session")
public class TrackSessionController {
	
	@Autowired TrackSessionService trackSessionService;
	
	@PostMapping(value = "/book")
	public ResponseEntity<TrackSessionDto> bookSession(@RequestBody TrackSessionDto trackSessionDto){
		System.out.println(trackSessionDto);
		TrackSession trackSession = trackSessionService.saveTrackSessionBooking(trackSessionDto);
		return new ResponseEntity<>(trackSessionDto , HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/book")
	public ResponseEntity<List<TrackSession>> getAllBooking(){
		List<TrackSession> allBooking = trackSessionService.getAllBooking();
		
		return new ResponseEntity<>(allBooking, HttpStatus.OK);
	}
	
	@GetMapping(value = "/book/{id}")
	public ResponseEntity<List<TrackSession>> getByDriver(@PathVariable Long id){
		List<TrackSession> booking = trackSessionService.getByDriver(id);
		return new ResponseEntity<>(booking, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/book/{id}")
	public ResponseEntity<?> deleteSessioById(@PathVariable Long id){
		trackSessionService.deleteSession(id);
		return new ResponseEntity<>( HttpStatus.OK);
	}

	
}
