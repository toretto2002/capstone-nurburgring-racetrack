package com.epicode.Spring.entities;

import java.time.LocalDateTime;
import com.epicode.Spring.enumerators.TrackExperienceLevel;
import com.epicode.Spring.security.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Table(name = "track_sessions")
public class TrackSession {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 private LocalDateTime dateTime;
	 
	 @ManyToOne
	 private User driver;
	 
	 @ManyToOne(fetch = FetchType.EAGER)
	 private RentableCar rentableCar;
	 
	 @ManyToOne(fetch = FetchType.EAGER)
	 private PrivateCar privateCar;
	 
	 private Double sessionPrice;
	 
	 @Enumerated
	 private TrackExperienceLevel experienceLevel;
	 
	 private Double duration;
	 
	 private String notes;
}
