package com.alexcasey.nasa_apod.service.apod;

import com.alexcasey.nasa_apod.model.Apod;
import com.alexcasey.nasa_apod.model.Inventory;
import com.alexcasey.nasa_apod.model.Wallet;

public interface IApodService {
    
    boolean canAffordApod(Wallet wallet);

    Apod saveApodToInventory(Apod apod, Inventory inventory);

    Apod getApod();
}
