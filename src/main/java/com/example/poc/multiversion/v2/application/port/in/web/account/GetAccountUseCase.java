package com.example.poc.multiversion.v2.application.port.in.web.account;

public interface GetAccountUseCase {
    AccountDTO getById(String id);
}
