package com.alexcasey.nasa_apod.service.wallet;

import com.alexcasey.nasa_apod.model.Wallet;

public interface IWalletService {
    Wallet createWallet();
    void deleteWallet(String username);
    void deposit(String username, double amount);
    void withdraw(String username, double amount);
    double getBalance(String username);
    void transfer(String sender, String receiver, double amount);
}
