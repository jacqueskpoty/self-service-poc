package com.example.poc.adapter.in.web;

import com.example.poc.adapter.out.persistence.AccountDocumentRepository;
import com.example.poc.application.port.in.web.account.AccountDTO;
import com.example.poc.application.port.in.web.account.AccountUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class is a RestController using Lombok annotation to avoid boiler plate code
 * Hover the mouse on each of the annotation to see what they do
 * This class represents the account controller
 * It takes HTTP requests and forward them to the application module
 * It's an IN ADAPTER
 */
@Slf4j
@RestController
@RequestMapping(path = "api")
@RequiredArgsConstructor
public class AccountController {

    /**
     * Initializing the application's IN PORT so as to consume service from the application
     * We communicate with the application through this port
     * Particularly requests related to an Account(Business term)
     * */
    private final AccountUseCase accountUseCase;
    private final AccountDocumentRepository accountDocumentRepository;

    /**
     * Creating a new account
     * Requires a Json representation of the DTO class
     */
    @PostMapping("/account")
    public ResponseEntity create(@RequestBody AccountDTO account) {
        return new ResponseEntity(accountUseCase.create(account), HttpStatus.OK);
    }

    /**
     * Get an account by the account ID
     * @param accountId
     * @return a responseEntity of the account fecthed
     */
    @GetMapping("/account/{accountId}")
    public ResponseEntity getById(@PathVariable("accountId") String accountId) {

        accountDocumentRepository.findAll();
        return new ResponseEntity(accountUseCase.getById(accountId), HttpStatus.OK);
    }
}
