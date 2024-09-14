package com.alexcasey.nasa_apod.service.inventory;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alexcasey.nasa_apod.model.Account;
import com.alexcasey.nasa_apod.model.Inventory;
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
        inventory.setItems(List.of());
        return inventory;
    }

}
