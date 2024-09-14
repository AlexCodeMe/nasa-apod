package com.alexcasey.nasa_apod.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal rubies;
    private BigDecimal emeralds;
    private BigDecimal sapphires;
    private BigDecimal diamonds;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public boolean deductRubies(BigDecimal amount) {
        if (rubies.compareTo(amount) >= 0) {
            rubies = rubies.subtract(amount);
            return true;
        } else {
            return false;
        }
    }
}
