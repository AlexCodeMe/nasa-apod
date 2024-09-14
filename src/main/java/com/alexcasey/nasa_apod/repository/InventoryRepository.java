package com.alexcasey.nasa_apod.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexcasey.nasa_apod.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    
}
