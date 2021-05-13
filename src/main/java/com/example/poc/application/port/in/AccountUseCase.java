package com.example.poc.application.port.in;

import com.example.poc.application.port.dto.AccountDto;

public interface AccountUseCase {

    AccountDto create(AccountDto accountDto);
    AccountDto getById(String id);
}
