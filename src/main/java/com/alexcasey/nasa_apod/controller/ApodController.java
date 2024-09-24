package com.alexcasey.nasa_apod.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alexcasey.nasa_apod.enums.CurrencyEnum;
import com.alexcasey.nasa_apod.model.Apod;
import com.alexcasey.nasa_apod.service.apod.IApodService;
import com.alexcasey.nasa_apod.service.wallet.IWalletService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/apods")
public class ApodController {

    private final IApodService apodService;
    private final IWalletService walletService;

    private String getAuthenticatedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    private <T> ResponseEntity<T> executeApodOperation(CurrencyEnum currency, Integer price, Supplier<T> operation) {
        String username = getAuthenticatedUsername();
        if (!walletService.canAffordQuery(username, currency, price)) {
            return ResponseEntity.badRequest().build();
        }
        walletService.withdraw(username, price, currency);
        return ResponseEntity.ok(operation.get());
    }

    /**
     * Get the Astronomy Picture of the Day
     * costs 1 ruby, emerald, or sapphire
     * 
     * @return ResponseEntity<Apod>
     */
    @GetMapping
    public ResponseEntity<Apod> getApod(@RequestParam CurrencyEnum currency) {
        return executeApodOperation(currency, 1, apodService::getApod);
    }

    /**
     * Get the Astronomy Picture of the Day by Date
     * costs 3 ruby, sapphire, or emerald
     * 
     * @return ResponseEntity<Apod>
     */
    @GetMapping("/by-date")
    public ResponseEntity<Apod> getApodByDate(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam CurrencyEnum currency) {
        return executeApodOperation(currency, 3, () -> apodService.getApodByDate(date));
    }

    /**
     * Get the Astronomy Picture of the Day by Date range
     * costs 1 ruby, sapphire, or emerald per day
     * 
     * @return ResponseEntity<List<Apod>>
     */
    @GetMapping("/by-date-range")
    public ResponseEntity<List<Apod>> getApodByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start_date,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end_date,
            @RequestParam CurrencyEnum currency) {

        int price = (int) start_date.datesUntil(end_date).count();
        return executeApodOperation(currency, price, () -> apodService.getApodByDateRange(start_date, end_date));
    }

    /**
     * Get the count random Astronomy Picture of the Days
     * costs 1 ruby, sapphire, or emerald per picture
     * 
     * @return ResponseEntity<List<Apod>>
     */
    @GetMapping("/random")
    public ResponseEntity<List<Apod>> getRandomApods(@RequestParam Integer count,
            @RequestParam CurrencyEnum currency) {
   
        return executeApodOperation(currency, count, () -> apodService.getCountRandomApods(count));
    }
}
