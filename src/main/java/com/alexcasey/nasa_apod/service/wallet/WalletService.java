package com.alexcasey.nasa_apod.service.wallet;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.alexcasey.nasa_apod.model.Wallet;
import com.alexcasey.nasa_apod.repository.AccountRepository;
import com.alexcasey.nasa_apod.repository.WalletRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletService implements IWalletService {

    private final WalletRepository walletRepository;
    private final AccountRepository accountRepository;

    @Override
    public Wallet createWallet() {
        Wallet wallet = new Wallet();
        wallet.setRubies(BigDecimal.ZERO);
        wallet.setEmeralds(BigDecimal.ZERO);
        wallet.setSapphires(BigDecimal.ZERO);
        wallet.setDiamonds(BigDecimal.ZERO);
        return wallet;
    }

    @Override
    public void deleteWallet(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteWallet'");
    }

    @Override
    public void deposit(String username, double amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deposit'");
    }

    @Override
    public void withdraw(String username, double amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'withdraw'");
    }

    @Override
    public double getBalance(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBalance'");
    }

    @Override
    public void transfer(String sender, String receiver, double amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'transfer'");
    }

}
