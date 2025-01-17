package com.alexcasey.nasa_apod.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexcasey.nasa_apod.model.Account;
import com.alexcasey.nasa_apod.model.Wallet;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);

    Wallet findWalletByUsername(String username);
}
