package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.Roles;

public interface RoleRepository extends JpaRepository<Roles, Long> {

}
