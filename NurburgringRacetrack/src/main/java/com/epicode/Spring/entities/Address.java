package com.epicode.Spring.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "addresses")
public class Address {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String street;
	
	@Column(nullable = false)
    private String streetNumber;
	
	@Column(nullable = false)
    private String city;
	
	@Column(nullable = false)
    private String province;
	
	@Column(nullable = false)
    private String postalCode;
	
	@Column(nullable = false)
    private String country;
}
