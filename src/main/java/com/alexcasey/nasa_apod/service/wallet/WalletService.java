package com.alexcasey.nasa_apod.service.wallet;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alexcasey.nasa_apod.enums.CurrencyEnum;
import com.alexcasey.nasa_apod.exception.AccountNotFoundException;
import com.alexcasey.nasa_apod.exception.WalletNotFoundException;
import com.alexcasey.nasa_apod.model.Account;
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
        wallet.setRubies(100);
        wallet.setEmeralds(100);
        wallet.setSapphires(100);
        wallet.setDiamonds(10);
        return wallet;
    }

    @Override
    public void deposit(String username, Integer amount, CurrencyEnum currency) {
        Wallet wallet = accountRepository.findByUsername(username).orElseThrow().getWallet();
        switch (currency) {
            case RUBIES -> wallet.setRubies(wallet.getRubies() + amount);
            case EMERALDS -> wallet.setEmeralds(wallet.getEmeralds() + amount);
            case SAPPHIRES -> wallet.setSapphires(wallet.getSapphires() + amount);
            case DIAMONDS -> wallet.setDiamonds(wallet.getDiamonds() + amount);
            default -> throw new UnsupportedOperationException("Unsupported currency: " + currency);
        }
        walletRepository.save(wallet);
    }

    @Override
    public void withdraw(String username, Integer amount, CurrencyEnum currency) {
        Wallet wallet = accountRepository.findByUsername(username).orElseThrow().getWallet();
        switch (currency) {
            case RUBIES -> {
                Integer rubies = wallet.getRubies();
                if (rubies < amount) {
                    throw new IllegalArgumentException("Insufficient funds: " + wallet.getRubies());
                } else {
                    wallet.setRubies(rubies - amount);
                }
            }
            case EMERALDS -> {
                Integer emeralds = wallet.getEmeralds();
                if (emeralds < amount) {
                    throw new IllegalArgumentException("Insufficient funds: " + wallet.getEmeralds());
                } else {
                    wallet.setEmeralds(emeralds - amount);
                }
            }
            case SAPPHIRES -> {
                Integer sapphires = wallet.getSapphires();
                if (sapphires < amount) {
                    throw new IllegalArgumentException("Insufficient funds: " + wallet.getSapphires());
                } else {
                    wallet.setSapphires(sapphires - amount);
                }
            }
            case DIAMONDS -> {
                if (wallet.getDiamonds() < amount) {
                    throw new IllegalArgumentException("Insufficient funds: " + wallet.getDiamonds());
                } else {
                    wallet.setDiamonds(wallet.getDiamonds() - amount);
                }
            }
            default -> throw new UnsupportedOperationException("Unsupported currency: " + currency);
        }
        walletRepository.save(wallet);
    }

    @Override
    public Integer getBalance(String username, CurrencyEnum currency) {
        return switch (currency) {
            case RUBIES -> accountRepository.findByUsername(username).orElseThrow().getWallet().getRubies();
            case EMERALDS -> accountRepository.findByUsername(username).orElseThrow().getWallet().getEmeralds();
            case SAPPHIRES -> accountRepository.findByUsername(username).orElseThrow().getWallet().getSapphires();
            case DIAMONDS -> accountRepository.findByUsername(username).orElseThrow().getWallet().getDiamonds();
            default -> throw new UnsupportedOperationException("Unsupported currency: " + currency);
        };
    }

    @Transactional
    @Override
    public void transfer(String sender, String receiver, Integer amount, CurrencyEnum currency) {
        Wallet senderWallet;
        Wallet receiverWallet;
        switch (currency) {
            case RUBIES -> {
                senderWallet = accountRepository.findByUsername(sender).orElseThrow().getWallet();
                if (senderWallet.getRubies() < amount) {
                    throw new IllegalArgumentException("Insufficient funds: " + senderWallet.getRubies());
                }
                receiverWallet = accountRepository.findByUsername(receiver).orElseThrow().getWallet();
                senderWallet.setRubies(senderWallet.getRubies() - amount);
                receiverWallet.setRubies(receiverWallet.getRubies() + amount);
            }
            case EMERALDS -> {
                senderWallet = accountRepository.findByUsername(sender).orElseThrow().getWallet();
                if (senderWallet.getEmeralds() < amount) {
                    throw new IllegalArgumentException("Insufficient funds: " + senderWallet.getEmeralds());
                }
                receiverWallet = accountRepository.findByUsername(receiver).orElseThrow().getWallet();
                senderWallet.setEmeralds(senderWallet.getEmeralds() - amount);
                receiverWallet.setEmeralds(receiverWallet.getEmeralds() + amount);
            }
            case SAPPHIRES -> {
                senderWallet = accountRepository.findByUsername(sender).orElseThrow().getWallet();
                if (senderWallet.getSapphires() < amount) {
                    throw new IllegalArgumentException("Insufficient funds: " + senderWallet.getSapphires());
                }
                receiverWallet = accountRepository.findByUsername(receiver).orElseThrow().getWallet();
                senderWallet.setSapphires(senderWallet.getSapphires() - amount);
                receiverWallet.setSapphires(receiverWallet.getSapphires() + amount);
            }
            case DIAMONDS -> {
                senderWallet = accountRepository.findByUsername(sender).orElseThrow().getWallet();
                if (senderWallet.getDiamonds() < amount) {
                    throw new IllegalArgumentException("Insufficient funds: " + senderWallet.getDiamonds());
                }
                receiverWallet = accountRepository.findByUsername(receiver).orElseThrow().getWallet();
                senderWallet.setDiamonds(senderWallet.getDiamonds() - amount);
                receiverWallet.setDiamonds(receiverWallet.getDiamonds() + amount);
            }
            default -> throw new UnsupportedOperationException("Unsupported currency: " + currency);
        }
        walletRepository.save(senderWallet);
        walletRepository.save(receiverWallet);
    }

    @Override
    public boolean canAffordQuery(String username, CurrencyEnum currency, Integer price) {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + username));
        Wallet wallet = account.getWallet();
        if (wallet == null) {
            throw new WalletNotFoundException("Wallet not found for account: " + username);
        }
        return switch (currency) {
            case RUBIES -> wallet.getRubies() >= price;
            case EMERALDS -> wallet.getEmeralds() >= price;
            case SAPPHIRES -> wallet.getSapphires() >= price;
            case DIAMONDS -> wallet.getDiamonds() >= price;
            default -> throw new UnsupportedOperationException("Unsupported currency: " + currency);
        };
    }

}
