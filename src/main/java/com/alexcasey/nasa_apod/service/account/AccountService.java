package com.alexcasey.nasa_apod.service.account;

import java.util.Collections;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alexcasey.nasa_apod.dto.AccountDto;
import com.alexcasey.nasa_apod.dto.AuthDto;
import com.alexcasey.nasa_apod.enums.RoleEnum;
import com.alexcasey.nasa_apod.exception.AlreadyExistsException;
import com.alexcasey.nasa_apod.model.Account;
import com.alexcasey.nasa_apod.model.Inventory;
import com.alexcasey.nasa_apod.model.Role;
import com.alexcasey.nasa_apod.model.Wallet;
import com.alexcasey.nasa_apod.repository.AccountRepository;
import com.alexcasey.nasa_apod.repository.RoleRepository;
import com.alexcasey.nasa_apod.request.AuthRequest;
import com.alexcasey.nasa_apod.service.inventory.InventoryService;
import com.alexcasey.nasa_apod.service.wallet.WalletService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final WalletService walletService;
    private final InventoryService inventoryService;
    private final RoleRepository roleRepository;

    @Transactional
    @Override
    public Account createAccount(AuthRequest request) {
        if (accountRepository.findByUsername(request.getUsername()) != null) {
            throw new AlreadyExistsException("Username already exists");
        }

        Account newAccount = new Account();
        newAccount.setUsername(request.getUsername());
        newAccount.setPassword(passwordEncoder.encode(request.getPassword()));

        Wallet wallet = walletService.createWallet();
        newAccount.setWallet(wallet);
        wallet.setAccount(newAccount);

        Inventory inventory = inventoryService.createInventory();
        newAccount.setInventory(inventory);
        inventory.setAccount(newAccount);

        Role userRole = roleRepository.findByRole(RoleEnum.USER);
        newAccount.setRoles(Collections.singleton(userRole));

        return accountRepository.save(newAccount);
    }

    @Override
    public Account updateAccount(Account account) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAccount'");
    }

    @Override
    public void deleteAccount(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAccount'");
    }

    @Override
    public Account getAccountById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccountById'");
    }

    @Override
    public Account getAccountByUsername(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccountByUsername'");
    }

    @Override
    public AccountDto convertAccountToAccountDto(Account account) {
        return null;
    }

    @Override
    public AuthDto convertAccountToAuthDto(Account account) {
        return null;
    }

}
