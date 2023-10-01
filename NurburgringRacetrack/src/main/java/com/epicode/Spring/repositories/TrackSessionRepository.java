package com.epicode.Spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epicode.Spring.entities.TrackSession;
import com.epicode.Spring.security.entity.User;


@Repository
public interface TrackSessionRepository extends JpaRepository<TrackSession, Long> {
	
	public List<TrackSession> findByDriver(User driver);
}
