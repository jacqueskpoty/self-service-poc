package com.example.poc.adapter.in.web;

import com.example.poc.application.port.dto.AccountDto;
import com.example.poc.application.port.in.AccountUseCase;
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

    @PostMapping("/account")
    public ResponseEntity create(@RequestBody AccountDto accountDto) {
        return new ResponseEntity(accountUseCase.create(accountDto), HttpStatus.OK);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity getById(@PathVariable("accountId") String accountId) {
        return new ResponseEntity(accountUseCase.getById(accountId), HttpStatus.OK);
    }
}
