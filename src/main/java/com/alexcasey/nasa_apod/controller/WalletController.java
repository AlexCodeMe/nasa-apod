package com.alexcasey.nasa_apod.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alexcasey.nasa_apod.enums.CurrencyEnum;
import com.alexcasey.nasa_apod.service.account.IAccountService;
import com.alexcasey.nasa_apod.service.wallet.IWalletService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/wallet")
public class WalletController {

    private final IWalletService walletService;
    private final IAccountService accountService;

    private String getAuthenticatedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/{currency}")
    public ResponseEntity<Integer> getBalance(@PathVariable CurrencyEnum currency) {
        try {
            Integer balance = walletService.getBalance(getAuthenticatedUsername(), currency);
            return ResponseEntity.ok(balance);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/deposit/{currency}")
    public ResponseEntity<String> deposit(@PathVariable CurrencyEnum currency, @RequestParam Integer amount) {
        try {
            walletService.deposit(getAuthenticatedUsername(), amount, currency);
            return ResponseEntity.ok("Deposit successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Deposit failed: " + e.getMessage());
        }
    }

    @PostMapping("/transfer/{currency}/to/{username}")
    public ResponseEntity<String> transfer(
            @PathVariable CurrencyEnum currency,
            @PathVariable String receiverUsername,
            @RequestParam Integer amount) {
        try {
            walletService.transfer(getAuthenticatedUsername(), receiverUsername, amount, currency);
            return ResponseEntity.ok("Transfer successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Transfer failed: " + e.getMessage());
        }
    }
}