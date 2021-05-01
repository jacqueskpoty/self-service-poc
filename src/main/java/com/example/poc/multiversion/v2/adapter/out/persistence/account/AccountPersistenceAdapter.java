package com.example.poc.multiversion.v2.adapter.out.persistence.account;

import com.example.poc.multiversion.v2.application.domain.Account;
import com.example.poc.multiversion.v2.application.port.out.persistence.LoadAccountPort;
import com.example.poc.multiversion.v2.application.port.out.persistence.SaveAccountPort;
import com.example.poc.multiversion.v2.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements LoadAccountPort, SaveAccountPort {

    private final AccountEntityRepository accountEntityRepository;
    private final AccountMapper accountMapper;

    @Override
    public Optional<Account> getAccountById(String id) {
        return Optional.of
                (accountMapper.toDomain
                (accountEntityRepository.findById(id).get()));
    }

    @Override
    public Account saveAccount(Account account) {
        return accountMapper.toDomain(accountEntityRepository.save(accountMapper.toEntity(account)));
    }
}
