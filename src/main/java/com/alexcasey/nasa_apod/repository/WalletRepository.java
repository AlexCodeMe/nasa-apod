package com.alexcasey.nasa_apod.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexcasey.nasa_apod.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
