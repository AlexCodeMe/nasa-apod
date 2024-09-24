package com.alexcasey.nasa_apod.service.inventory;

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
import com.alexcasey.nasa_apod.repository.ItemRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService implements IInventoryService {

    private final AccountRepository accountRepository;
    private final InventoryRepository inventoryRepository;
    private final ItemRepository itemRepository;

    @Override
    public Inventory createInventory() {
        Inventory inventory = new Inventory();
        inventory.setAccount(new Account());
        inventory.setItems(new ArrayList<>());
        return inventory;
    }

    @Override
    public Inventory getInventoryByUsername(String username) {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new AccountNotFoundException("Account not found for user: " + username));
        return account.getInventory();
    }

    @Override
    public Inventory getOrCreateInventoryByAccountId(Long accountId) {
        return inventoryRepository.findByAccountId(accountId)
            .orElseGet(() -> {
                Account account = accountRepository.findById(accountId)
                    .orElseThrow(() -> new EntityNotFoundException("Account not found"));
                Inventory newInventory = new Inventory();
                newInventory.setAccount(account);
                return inventoryRepository.save(newInventory);
            });
    }

    @Transactional
    @Override
    public Apod saveApodToInventory(Apod apod, String username) {
        Inventory inventory = getOrCreateInventoryByAccountId(accountRepository.findByUsername(username)
                .orElseThrow(() -> new AccountNotFoundException("Account not found for user: " + username))
                .getId());
        Item item = convertApodToItem(apod);
        item.setInventory(inventory);
        if (inventory.getItems() == null) {
            inventory.setItems(new ArrayList<>());
        }
        inventory.getItems().add(item);
        inventoryRepository.save(inventory);
        return apod;
    }

    @Transactional
    @Override
    public void transferItem(Long itemId, String buyerName, String sellerName) {
        Inventory sellerInventory = inventoryRepository.findInventoryByAccount_Username(sellerName);
        if (sellerInventory == null) {
            throw new IllegalArgumentException("Seller inventory not found for user: " + sellerName);
        }
        Inventory buyerInventory = inventoryRepository.findInventoryByAccount_Username(buyerName);
        if (buyerInventory == null) {
            throw new IllegalArgumentException("Buyer inventory not found for user: " + buyerName);
        }

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found: " + itemId));
        if (!item.getInventory().equals(sellerInventory)) {
            throw new IllegalArgumentException("Item does not belong to the seller's inventory");
        }

        sellerInventory.getItems().remove(item);
        item.setInventory(buyerInventory);
        buyerInventory.getItems().add(item);

        inventoryRepository.save(sellerInventory);
        inventoryRepository.save(buyerInventory);
        itemRepository.save(item);
    }

    @Transactional
    @Override
    public Item convertApodToItem(Apod apod) {
        Item item = new Item();
        item.setTitle(apod.getTitle());
        item.setExplanation(apod.getExplanation());
        item.setUrl(apod.getUrl());
        item.setPrice(new Random().nextInt(100));
        return item;
    }

}
