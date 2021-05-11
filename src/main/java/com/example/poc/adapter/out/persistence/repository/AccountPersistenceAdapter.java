package com.example.poc.adapter.out.persistence.repository;


import com.example.poc.adapter.out.persistence.mapper.AccountDocumentMapper;
import com.example.poc.application.port.out.persistence.AccountPort;
import com.example.poc.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements AccountPort {

    private final AccountDocumentRepository accountDocumentRepository;

    private final AccountDocumentMapper accountMapper;

    @Override
    public Optional<Account> getAccountById(String id) {
        return Optional.of
                (accountMapper.toDomain
                        (accountDocumentRepository.findById(id).get()));
    }


    @Override
    public Account saveAccount(Account account) {
        return accountMapper.toDomain
                (accountDocumentRepository.save
                        (accountMapper.toDocument(account)));
    }
}
