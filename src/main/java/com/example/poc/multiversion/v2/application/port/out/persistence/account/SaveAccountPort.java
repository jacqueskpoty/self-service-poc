package com.example.poc.multiversion.v2.application.port.out.persistence.account;

import com.example.poc.multiversion.v2.application.domain.Account;

public interface SaveAccountPort {
    Account saveAccount(Account account);
}
