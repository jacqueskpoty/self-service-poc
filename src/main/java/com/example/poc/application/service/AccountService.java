package com.example.poc.application.service;

import com.example.poc.application.port.in.web.usecase.AccountUseCase;
import com.example.poc.application.port.out.persistence.AccountPort;
import com.example.poc.domain.Account;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountService implements AccountUseCase {

    private final AccountPort accountPort;

    @Override
    public Account create(Account account) {
       return accountPort.saveAccount(account);
    }

    @Override
    public Account getById(String id) {
        return accountPort.getAccountById(id).orElseGet(Account::new);
    }
}
