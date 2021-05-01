package com.example.poc.multiversion.v2.application.service;

import com.example.poc.multiversion.v2.application.domain.Account;
import com.example.poc.multiversion.v2.application.port.in.web.account.AccountDTO;
import com.example.poc.multiversion.v2.application.port.in.web.account.GetAccountUseCase;
import com.example.poc.multiversion.v2.application.port.out.persistence.LoadAccountPort;
import com.example.poc.multiversion.v2.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
class GetAccountService implements GetAccountUseCase {

    private final LoadAccountPort loadAccountPort;

    @Override
    public AccountDTO getById(String id) {
        return loadAccountPort.getAccountById(id)
                .map(Account::toDTO)
                .orElseGet(AccountDTO::new);
    }
}
