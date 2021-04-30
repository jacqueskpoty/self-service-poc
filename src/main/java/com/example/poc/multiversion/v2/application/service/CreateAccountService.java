package com.example.poc.multiversion.v2.application.service;

import com.example.poc.multiversion.v2.application.port.in.web.account.AccountDTO;
import com.example.poc.multiversion.v2.application.port.in.web.account.CreateAccountUseCase;
import com.example.poc.multiversion.v2.application.port.out.persistence.account.SaveAccountPort;
import com.example.poc.multiversion.v2.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@UseCase
@RequiredArgsConstructor
class CreateAccountService implements CreateAccountUseCase {

    private final SaveAccountPort saveAccountPort;

    @Override
    public AccountDTO create(AccountDTO accountDTO) {
        return saveAccountPort.saveAccount(accountDTO.getAccount()).toDTO();
    }
}
