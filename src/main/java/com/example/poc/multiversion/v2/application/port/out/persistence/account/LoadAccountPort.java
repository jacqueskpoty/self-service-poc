package com.example.poc.multiversion.v2.application.port.out.persistence.account;

import com.example.poc.multiversion.v2.application.domain.Account;

import java.util.Optional;

public interface LoadAccountPort {
    Optional<Account> getAccountById(String id);
}
