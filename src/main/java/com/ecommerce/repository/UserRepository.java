package com.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUserName(String username);

	 boolean existsByUserName(String userName);

	    boolean existsByEmail(String email);
}
