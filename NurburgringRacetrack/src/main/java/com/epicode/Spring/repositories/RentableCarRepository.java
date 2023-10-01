package com.epicode.Spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epicode.Spring.entities.RentableCar;

public interface RentableCarRepository extends JpaRepository<RentableCar, Long> {

}
