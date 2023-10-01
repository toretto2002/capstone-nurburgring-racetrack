package com.epicode.Spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epicode.Spring.security.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}
