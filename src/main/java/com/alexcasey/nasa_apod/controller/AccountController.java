package com.alexcasey.nasa_apod.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexcasey.nasa_apod.model.Account;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/account")
public class AccountController {
    
    @GetMapping
    public Account getAccount() {
        return null;
    }

    @PostMapping("/update-password")
    public void updatePassword() {
        
    }
}
