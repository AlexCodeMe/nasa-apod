package com.alexcasey.nasa_apod.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexcasey.nasa_apod.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
