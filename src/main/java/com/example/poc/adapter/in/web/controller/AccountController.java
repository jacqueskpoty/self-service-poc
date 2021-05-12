package com.example.poc.adapter.in.web.controller;

import com.example.poc.adapter.in.web.dto.AccountDTO;
import com.example.poc.adapter.in.web.mapper.AccountDTOMapper;
import com.example.poc.application.port.in.web.usecase.AccountUseCase;
import com.example.poc.domain.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "api")
@RequiredArgsConstructor
public class AccountController {

    private final AccountUseCase accountUseCase;
    private final AccountDTOMapper accountDTOMapper = AccountDTOMapper.INSTANCE;

    @PostMapping("/account")
    public ResponseEntity create(@RequestBody AccountDTO accountDTO) {

        Account account = accountDTOMapper.toDomain(accountDTO);
        account = accountUseCase.create(account);
        accountDTO = accountDTOMapper.toDto(account);
        return new ResponseEntity(accountDTO, HttpStatus.OK);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity getById(@PathVariable("accountId") String accountId) {

        Account account = accountUseCase.getById(accountId);
        AccountDTO accountDTO = accountDTOMapper.toDto(account);
        return new ResponseEntity(accountDTO, HttpStatus.OK);
    }
}
