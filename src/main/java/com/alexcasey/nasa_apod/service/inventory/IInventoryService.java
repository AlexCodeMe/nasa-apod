package com.alexcasey.nasa_apod.service.inventory;

import com.alexcasey.nasa_apod.model.Apod;
import com.alexcasey.nasa_apod.model.Inventory;
import com.alexcasey.nasa_apod.model.Item;

public interface IInventoryService {
    Inventory createInventory();

    Apod saveApodToInventory(Apod apod, String username);

    Inventory getOrCreateInventoryByAccountId(Long accountId);

    void transferItem(Long itemId, String sellerId, String buyerId);

    Item convertApodToItem(Apod apod);

    Inventory getInventoryByUsername(String username);
}
