package com.alexcasey.nasa_apod.service.apod;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.alexcasey.nasa_apod.model.Apod;
import com.alexcasey.nasa_apod.model.Inventory;
import com.alexcasey.nasa_apod.model.Wallet;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApodService implements IApodService {

    @Value("${nasa.api.key}")
    private String apiKey;

    @Value("${nasa.api.url}")
    private String apiUrl;

    private final WebClient webClient;

    @Override
    public Apod getApod() {
        return null;
    }

    @Override
    public boolean canAffordApod(Wallet wallet) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canAffordApod'");
    }

    @Override
    public Apod saveApodToInventory(Apod Apod, Inventory inventory) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveApodToInventory'");
    }

}
