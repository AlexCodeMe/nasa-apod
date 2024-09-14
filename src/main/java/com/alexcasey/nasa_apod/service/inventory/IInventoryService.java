package com.alexcasey.nasa_apod.service.inventory;

import com.alexcasey.nasa_apod.model.Apod;
import com.alexcasey.nasa_apod.model.Inventory;

public interface IInventoryService {
    Inventory createInventory();

    Apod saveApodToInventory(Apod apod, Inventory inventory);

    Inventory getInventoryByUserId(Long userId);
}
