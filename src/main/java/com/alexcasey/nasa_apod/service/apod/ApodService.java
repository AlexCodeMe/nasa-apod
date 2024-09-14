package com.alexcasey.nasa_apod.service.apod;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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

    private final RestTemplate restTemplate;

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

    @Override
    public Apod getApod() {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .path("/apod")
                .queryParam("api_key", apiKey)
                .toUriString();
        return restTemplate.getForObject(url, Apod.class);
    }

    @Override
    public Apod getApodByDate(LocalDate date) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .path("/apod")
                .queryParam("api_key", apiKey)
                .queryParam("date", date.toString())
                .toUriString();
        return restTemplate.getForObject(url, Apod.class);
    }

    @Override
    public List<Apod> getApodByDateRange(LocalDate startDate, LocalDate endDate) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .path("/apod")
                .queryParam("api_key", apiKey)
                .queryParam("start_date", startDate.toString())
                .queryParam("end_date", endDate.toString())
                .toUriString();
        Apod[] apods = restTemplate.getForObject(url, Apod[].class);
        return Arrays.asList(apods);
    }

    @Override
    public List<Apod> getCountRandomApods(Integer count) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .path("/apod")
                .queryParam("api_key", apiKey)
                .queryParam("count", count.toString())
                .toUriString();
        Apod[] apods = restTemplate.getForObject(url, Apod[].class);
        return Arrays.asList(apods);
    }

}
