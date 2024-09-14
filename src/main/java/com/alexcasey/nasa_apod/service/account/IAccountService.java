package com.alexcasey.nasa_apod.service.account;

import com.alexcasey.nasa_apod.dto.AccountDto;
import com.alexcasey.nasa_apod.dto.AuthDto;
import com.alexcasey.nasa_apod.model.Account;
import com.alexcasey.nasa_apod.request.AuthRequest;

public interface IAccountService {
    Account createAccount(AuthRequest account);
    Account updateAccount(Account account);
    void deleteAccount(Long id);
    Account getAccountById(Long id);
    Account getAccountByUsername(String username);
    AccountDto convertAccountToAccountDto(Account account);
    AuthDto convertAccountToAuthDto(Account account);
}
