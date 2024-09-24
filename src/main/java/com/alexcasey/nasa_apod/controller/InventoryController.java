package com.alexcasey.nasa_apod.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alexcasey.nasa_apod.enums.CurrencyEnum;
import com.alexcasey.nasa_apod.model.Apod;
import com.alexcasey.nasa_apod.model.Inventory;
import com.alexcasey.nasa_apod.repository.InventoryRepository;
import com.alexcasey.nasa_apod.service.inventory.InventoryService;
import com.alexcasey.nasa_apod.service.wallet.WalletService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/inventory")
public class InventoryController {

    private final InventoryRepository inventoryRepository;
    private final InventoryService inventoryService;
    private final WalletService walletService;

    @GetMapping
    public List<Inventory> getInventories() {
        return inventoryRepository.findAll();
    }

    @GetMapping("/{username}")
    public Optional<Inventory> getInventory(@PathVariable Long username) {
        return Optional.of(inventoryService.getOrCreateInventoryByAccountId(username));
    }

    @PostMapping("/{username}")
    public Optional<Apod> addItem(@RequestBody Apod apod, @PathVariable String username) {
        return Optional
                .of(inventoryService.saveApodToInventory( apod,  username));
    }

    @DeleteMapping("/{itemId}")
    public void removeItem(@PathVariable Long itemId) {
        inventoryRepository.deleteById(itemId);
    }

    @GetMapping("/sell/{itemId}/{currency}")
    public void sellItem(@PathVariable Long itemId, @PathVariable CurrencyEnum currency,
            @RequestParam String sellerUsername, @RequestParam String buyerUsername, @RequestParam Integer price) {
        walletService.transfer(sellerUsername, buyerUsername, price, currency);
        inventoryService.transferItem(itemId, sellerUsername, buyerUsername);
    }
}
