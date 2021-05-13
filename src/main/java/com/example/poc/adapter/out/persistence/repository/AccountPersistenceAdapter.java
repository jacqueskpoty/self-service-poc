package com.example.poc.adapter.out.persistence.repository;


import com.example.poc.adapter.out.persistence.mapper.AccountDocumentMapper;
import com.example.poc.application.port.out.AccountPort;
import com.example.poc.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements AccountPort {

    private final AccountDocumentRepository accountDocumentRepository;

    private final AccountDocumentMapper accountDocumentMapper = AccountDocumentMapper.INSTANCE;

    @Override
    public Optional<Account> getAccountById(String id) {
        return  accountDocumentRepository.findById(id)
                .map(accountDocumentMapper::toDomain);
    }


    @Override
    public Account saveAccount(Account account) {
        return  Optional.of(accountDocumentMapper.toDocument(account))
                .map(accountDocumentRepository::save)
                .map(accountDocumentMapper::toDomain)
                .orElseGet(null);

    }
}
