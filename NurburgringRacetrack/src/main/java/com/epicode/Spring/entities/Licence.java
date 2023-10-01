package com.epicode.Spring.entities;

import java.time.LocalDate;

import com.epicode.Spring.security.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@NoArgsConstructor
@ToString(exclude = "user")
@Entity
@Table(name = "licenses")
public class Licence {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(mappedBy = "licence")
	@JsonIgnore
	private User user;
	
	@Column(nullable = false)
	private String licenceNumber;
	
	@Column(nullable = false)
	private LocalDate dateOfIssue;
	
	@Column(nullable = false)
	private LocalDate expirationDate;
	
	@Column(nullable = false)
	private String category;
	
	@Column(nullable = false)
	private String issuingAuthority;
	
	private byte[] photo;
}
