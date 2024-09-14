package com.alexcasey.nasa_apod.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
import com.alexcasey.nasa_apod.model.Wallet;
import com.alexcasey.nasa_apod.repository.AccountRepository;
import com.alexcasey.nasa_apod.service.account.IAccountService;
import com.alexcasey.nasa_apod.service.apod.IApodService;
import com.alexcasey.nasa_apod.service.inventory.IInventoryService;
import com.alexcasey.nasa_apod.service.wallet.IWalletService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/apods")
public class ApodController {

    private final IApodService apodService;
    private final IWalletService walletService;
    private final IInventoryService inventoryService;
    private final IAccountService accountService;

    private final AccountRepository accountRepository;

    private String getAuthenticatedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    private boolean canAffordApod(String username, CurrencyEnum currency, BigDecimal price) {
        Wallet wallet = accountRepository.findWalletByUsername(username);
        switch (currency) {
            case RUBIES -> {
                if (wallet.getRubies().compareTo(price) < 0) {
                    return false;
                }
            }
            case EMERALDS -> {
                if (wallet.getEmeralds().compareTo(price) < 0) {
                    return false;
                }
            }
            case SAPPHIRES -> {
                if (wallet.getSapphires().compareTo(price) < 0) {
                    return false;
                }
            }
            case DIAMONDS -> {
                if (wallet.getDiamonds().compareTo(price) < 0) {
                    return false;
                }
            }
            default -> throw new UnsupportedOperationException("Unsupported currency: " + currency);
        }
        return true;
    }

    /**
     * Get the Astronomy Picture of the Day
     * costs one ruby, emerald, or sapphire
     * 
     * @return
     */
    @GetMapping
    public ResponseEntity<Apod> getApod(@RequestParam CurrencyEnum currency) {

        String username = getAuthenticatedUsername();
        if (!canAffordApod(username, currency, BigDecimal.ONE)) {
            return ResponseEntity.badRequest().build();
        }
        walletService.withdraw(username, BigDecimal.ONE, currency);
        return ResponseEntity.ok(apodService.getApod());
    }

    /**
     * Get the Astronomy Picture of the Day by Date
     * costs 3 ruby, sapphire, or emerald
     * 
     * @return
     */
    @GetMapping("/by-date")
    public ResponseEntity<Apod> getApodByDate(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam CurrencyEnum currency) {

        String username = getAuthenticatedUsername();
        if (!canAffordApod(username, currency, BigDecimal.valueOf(3))) {
            return ResponseEntity.badRequest().build();
        }
        walletService.withdraw(username, BigDecimal.ONE, currency);
        return ResponseEntity.ok(apodService.getApodByDate(date));
    }

    /**
     * Get the Astronomy Picture of the Day by Date range
     * costs 1 ruby, sapphire, or emerald per day
     * 
     * @return
     */
    @GetMapping("/by-date-range")
    public ResponseEntity<List<Apod>> getApodByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start_date,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end_date) {
        return ResponseEntity.ok(apodService.getApodByDateRange(start_date, end_date));
    }

    /**
     * Get the count random Astronomy Picture of the Days
     * costs 1 ruby, sapphire, or emerald per picture
     * 
     * @return
     */
    @GetMapping("/random")
    public ResponseEntity<List<Apod>> getRandomApods(@RequestParam Integer count) {
        return ResponseEntity.ok(apodService.getCountRandomApods(count));
    }
}
