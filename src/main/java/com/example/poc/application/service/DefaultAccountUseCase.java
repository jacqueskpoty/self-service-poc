package com.example.poc.application.service;

import com.example.poc.application.port.dto.AccountDto;
import com.example.poc.application.port.dtomapper.AccountDtoMapper;
import com.example.poc.application.port.in.AccountUseCase;
import com.example.poc.application.port.out.AccountPort;
import com.example.poc.domain.Account;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultAccountUseCase implements AccountUseCase {

    private final AccountPort accountPort;
    private final AccountDtoMapper accountDtoMapper = AccountDtoMapper.INSTANCE;

    @Override
    public AccountDto create(AccountDto accountDto) {
        Account account = accountDtoMapper.toDomain(accountDto);
        account = accountPort.saveAccount(account);
        return accountDtoMapper.toDto(account);
    }

    @Override
    public AccountDto getById(String id) {
        Account account = accountPort.getAccountById(id).orElseGet(Account::new);
        return accountDtoMapper.toDto(account);
    }
}
