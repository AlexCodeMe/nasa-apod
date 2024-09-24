package com.alexcasey.nasa_apod.service.wallet;

import com.alexcasey.nasa_apod.enums.CurrencyEnum;
import com.alexcasey.nasa_apod.model.Wallet;

public interface IWalletService {
    Wallet createWallet();

    void deposit(String username, Integer amount, CurrencyEnum currency);

    void withdraw(String username, Integer amount, CurrencyEnum currency);

    Integer getBalance(String username, CurrencyEnum currency);

    void transfer(String sender, String receiver, Integer amount, CurrencyEnum currency);

    boolean canAffordQuery(String username, CurrencyEnum currency, Integer price);

    
}
