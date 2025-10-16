package com.Voyago_Travel.Voyago_Travel.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Voyago_Travel.Voyago_Travel.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	boolean existsByEmail(String email);

	boolean existsByMobile(Long mobile);

	Optional<User> findByEmail(String email);
}
