package com.alexcasey.nasa_apod.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexcasey.nasa_apod.enums.RoleEnum;
import com.alexcasey.nasa_apod.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findByRole(RoleEnum role);
}
