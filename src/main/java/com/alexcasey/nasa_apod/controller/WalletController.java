package com.alexcasey.nasa_apod.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/wallet")
public class WalletController {
    
    @GetMapping("/rubies")
    public BigDecimal getRubies() {
        return BigDecimal.ZERO;
    }
    @GetMapping("/emeralds")
    public BigDecimal getEmeralds() {
        return BigDecimal.ZERO;
    }
    @GetMapping("/sapphires")
    public BigDecimal getSapphires() {
        return BigDecimal.ZERO;
    }
    @GetMapping("/diamonds")
    public BigDecimal getDiamonds() {
        return BigDecimal.ZERO;
    }
    
}
