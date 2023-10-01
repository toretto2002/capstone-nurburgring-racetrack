package com.epicode.Spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epicode.Spring.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>  {

}
