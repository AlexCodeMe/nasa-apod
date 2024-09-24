package com.alexcasey.nasa_apod.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexcasey.nasa_apod.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findInventoryByAccount_Username(String username);

    Inventory findInventoryByAccountId(Long accountId);

    Optional<Inventory> findByAccountId(Long accountId);
    
}
