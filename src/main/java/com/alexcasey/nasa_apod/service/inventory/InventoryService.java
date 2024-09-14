package com.alexcasey.nasa_apod.service.inventory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alexcasey.nasa_apod.exception.AccountNotFoundException;
import com.alexcasey.nasa_apod.model.Account;
import com.alexcasey.nasa_apod.model.Apod;
import com.alexcasey.nasa_apod.model.Inventory;
import com.alexcasey.nasa_apod.model.Item;
import com.alexcasey.nasa_apod.repository.AccountRepository;
import com.alexcasey.nasa_apod.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService implements IInventoryService {

    private final AccountRepository accountRepository;
    private final InventoryRepository inventoryRepository;

    @Override
    public Inventory createInventory() {
        Inventory inventory = new Inventory();
        inventory.setAccount(new Account());
        inventory.setItems(new ArrayList<>());
        return inventory;
    }

    @Override
    public Inventory getInventoryByUserId(Long userId) {
        Account account = accountRepository.findById(userId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found for user: " + userId));
        return account.getInventory();
    }

    @Override
    @Transactional
    public Apod saveApodToInventory(Apod apod, Inventory inventory) {
        Item item = convertApodToItem(apod);
        item.setInventory(inventory);
        inventory.getItems().add(item);
        inventoryRepository.save(inventory);
        return apod;
    }

    private Item convertApodToItem(Apod apod) {
        Item item = new Item();
        item.setTitle(apod.getTitle());
        item.setExplanation(apod.getExplanation());
        item.setUrl(apod.getUrl());
        item.setPrice(BigDecimal.valueOf(new Random().nextInt(100)));
        return item;
    }

}
