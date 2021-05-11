package com.example.poc.application.port.in.web.usecase;

import com.example.poc.domain.Account;

public interface AccountUseCase {

    Account create(Account account);
    Account getById(String id);
}
