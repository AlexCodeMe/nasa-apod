package com.alexcasey.nasa_apod.service.wallet;

import java.math.BigDecimal;

import com.alexcasey.nasa_apod.enums.CurrencyEnum;
import com.alexcasey.nasa_apod.model.Wallet;

public interface IWalletService {
    Wallet createWallet();

    void deposit(String username, BigDecimal amount, CurrencyEnum currency);

    void withdraw(String username, BigDecimal amount, CurrencyEnum currency);

    BigDecimal getBalance(String username, CurrencyEnum currency);

    void transfer(String sender, String receiver, BigDecimal amount, CurrencyEnum currency);
}
