package com.example.poc.application.port.out.persistence;

import com.example.poc.domain.Account;

import java.util.Optional;

public interface AccountPort {

    Optional<Account> getAccountById(String id);

    Account saveAccount(Account account);
}
