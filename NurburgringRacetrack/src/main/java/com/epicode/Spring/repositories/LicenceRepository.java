package com.epicode.Spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epicode.Spring.entities.Licence;

@Repository
public interface LicenceRepository extends JpaRepository<Licence, Long> {

}
