package com.alexcasey.nasa_apod.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexcasey.nasa_apod.model.Account;
import com.alexcasey.nasa_apod.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/account")
public class AccountController {

    private final AccountRepository accountRepository;
    
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @PostMapping("/update-password")
    public void updatePassword() {
        
    }
}
