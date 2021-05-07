package com.example.poc.application.service;

import com.example.poc.application.port.in.web.account.AccountDTO;
import com.example.poc.application.port.in.web.account.AccountDTOMapper;
import com.example.poc.application.port.in.web.account.AccountUseCase;
import com.example.poc.application.port.out.persistence.AccountPort;
import com.example.poc.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Account Service Implementation, package private do not need to be directly accessed by any class
 * This class is part of the core of the application
 * It takes request coming through the PORT IN and foward them to the PORT OUT
 * THis class deals with use cases only related to the ACCOUNT Domain
 * Uses Lombok Annotation
 */
@Service
@RequiredArgsConstructor
class AccountService implements AccountUseCase {

    /**
     * Initializing the PORT OUT
     */
    private final AccountPort accountPort;
    private final AccountDTOMapper accountDTOMapper;

    /**
     * Takes a create request from the PORT IN WEB and forwards it to the PORT OUT
     * so as to create a new account
     * @param accountDTO the object the WEB IN sends
     * @return AcountDTO object which the WEB IN needs
     */
    @Override
    public AccountDTO create(AccountDTO accountDTO) {
       return accountDTOMapper.toDto(accountPort.saveAccount(accountDTO.getAccount()));
    }

    /**
     * Takes a get account by ID request from the PORT WEB IN and forwards it to the PORT OUT
     * @param id of the account that needs to be fetched
     * @return AcountDTO object which the WEB IN needs
     */
    @Override
    public AccountDTO getById(String id) {
        return accountPort.getAccountById(id)
                .map(accountDTOMapper::toDto)
                .orElseGet(AccountDTO::new);
    }
}
