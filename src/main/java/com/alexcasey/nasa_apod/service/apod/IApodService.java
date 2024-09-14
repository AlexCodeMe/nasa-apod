package com.alexcasey.nasa_apod.service.apod;

import java.time.LocalDate;
import java.util.List;

import com.alexcasey.nasa_apod.model.Apod;
import com.alexcasey.nasa_apod.model.Inventory;
import com.alexcasey.nasa_apod.model.Wallet;

public interface IApodService {
    boolean canAffordApod(Wallet wallet);

    Apod saveApodToInventory(Apod apod, Inventory inventory);

    Apod getApod();

    Apod getApodByDate(LocalDate date);

    List<Apod> getApodByDateRange(LocalDate startDate, LocalDate endDate);

    List<Apod> getCountRandomApods(Integer count);
}
