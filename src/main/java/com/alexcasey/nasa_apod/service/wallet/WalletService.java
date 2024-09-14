package com.alexcasey.nasa_apod.service.wallet;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alexcasey.nasa_apod.enums.CurrencyEnum;
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
    public void deposit(String username, BigDecimal amount, CurrencyEnum currency) {
        Wallet wallet = accountRepository.findByUsername(username).getWallet();
        switch (currency) {
            case RUBIES -> wallet.setRubies(wallet.getRubies().add(amount));
            case EMERALDS -> wallet.setEmeralds(wallet.getEmeralds().add(amount));
            case SAPPHIRES -> wallet.setSapphires(wallet.getEmeralds().add(amount));
            case DIAMONDS -> wallet.setDiamonds(wallet.getDiamonds().add(amount));
            default -> throw new UnsupportedOperationException("Unsupported currency: " + currency);
        }
        walletRepository.save(wallet);
    }

    @Override
    public void withdraw(String username, BigDecimal amount, CurrencyEnum currency) {
        Wallet wallet = accountRepository.findByUsername(username).getWallet();
        switch (currency) {
            case RUBIES -> wallet.setRubies(wallet.getRubies().subtract(amount));
            case EMERALDS -> wallet.setEmeralds(wallet.getEmeralds().subtract(amount));
            case SAPPHIRES -> wallet.setSapphires(wallet.getEmeralds().subtract(amount));
            case DIAMONDS -> wallet.setDiamonds(wallet.getDiamonds().subtract(amount));
            default -> throw new UnsupportedOperationException("Unsupported currency: " + currency);
        }
        walletRepository.save(wallet);
    }

    @Override
    public BigDecimal getBalance(String username, CurrencyEnum currency) {
        return switch(currency) {
            case RUBIES -> accountRepository.findByUsername(username).getWallet().getRubies();
            case EMERALDS -> accountRepository.findByUsername(username).getWallet().getEmeralds();
            case SAPPHIRES -> accountRepository.findByUsername(username).getWallet().getSapphires();
            case DIAMONDS -> accountRepository.findByUsername(username).getWallet().getDiamonds();
            default -> throw new UnsupportedOperationException("Unsupported currency: " + currency);
        };
    }

    @Transactional
    @Override
    public void transfer(String sender, String receiver, BigDecimal amount, CurrencyEnum currency) {
        Wallet senderWallet;
        Wallet receiverWallet;
        switch (currency) {
            case RUBIES -> {
                senderWallet = accountRepository.findByUsername(sender).getWallet();
                receiverWallet = accountRepository.findByUsername(receiver).getWallet();
                senderWallet.setRubies(senderWallet.getRubies().subtract(amount));
                receiverWallet.setRubies(receiverWallet.getRubies().add(amount));
            }
            case EMERALDS -> {
                senderWallet = accountRepository.findByUsername(sender).getWallet();
                receiverWallet = accountRepository.findByUsername(receiver).getWallet();
                senderWallet.setEmeralds(senderWallet.getEmeralds().subtract(amount));
                receiverWallet.setEmeralds(receiverWallet.getEmeralds().add(amount));
            }
            case SAPPHIRES -> {
                senderWallet = accountRepository.findByUsername(sender).getWallet();
                receiverWallet = accountRepository.findByUsername(receiver).getWallet();
                senderWallet.setSapphires(senderWallet.getSapphires().subtract(amount));
                receiverWallet.setSapphires(receiverWallet.getSapphires().add(amount));
            }
            case DIAMONDS -> {
                senderWallet = accountRepository.findByUsername(sender).getWallet();
                receiverWallet = accountRepository.findByUsername(receiver).getWallet();
                senderWallet.setDiamonds(senderWallet.getDiamonds().subtract(amount));
                receiverWallet.setDiamonds(receiverWallet.getDiamonds().add(amount));
            }
            default -> throw new UnsupportedOperationException("Unsupported currency: " + currency);
        }
        walletRepository.save(senderWallet);
        walletRepository.save(receiverWallet);
    }

}
