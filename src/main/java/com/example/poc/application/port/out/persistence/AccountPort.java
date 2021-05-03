package com.example.poc.application.port.out.persistence;

import com.example.poc.domain.Account;

import java.util.Optional;

/**
 * Interface used as a port to communicate with the persistence adapter
 * See implementation in the Adapter OUT package
 * Used to query data from whatever database we are using
 */
public interface AccountPort {
    /**
     * get an account from the database
     * @param id Account ID
     * @return Optional of Account
     */
    Optional<Account> getAccountById(String id);

    /**
     * save a new account in the database
     * @param account
     * @return the saved Account
     */
    Account saveAccount(Account account);
}
