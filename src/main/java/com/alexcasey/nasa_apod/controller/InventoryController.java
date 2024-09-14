package com.alexcasey.nasa_apod.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alexcasey.nasa_apod.model.Apod;
import com.alexcasey.nasa_apod.model.Inventory;

public class InventoryController {
    
    @GetMapping
    public Inventory getInventory() {
        return null;
    }

    @GetMapping("/{apodId}")
    public Apod getapod(@PathVariable Long apodId) {
        return null;
    }

    @PostMapping("/{apodId}")
    public void addapod(@RequestParam Apod apod, @PathVariable Long apodId) {
        
    }
    
    @DeleteMapping("/{apodId}")
    public void removeapod(@PathVariable Long apodId) {
        
    }

    @GetMapping("/sell/{apodId}")
    public void sellapod(@PathVariable Long apodId) {
        
    }

    @GetMapping("/buy/{apodId}")
    public void buyapod(@PathVariable Long apodId) {
        
    }
}
