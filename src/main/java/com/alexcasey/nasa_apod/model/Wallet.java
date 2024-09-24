package com.alexcasey.nasa_apod.model;

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
    private Integer rubies;
    private Integer emeralds;
    private Integer sapphires;
    private Integer diamonds;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public boolean deductRubies(Integer amount) {
        if (rubies >= amount) {
            rubies -= amount;
            return true;
        } else {
            return false;
        }
    }
}
