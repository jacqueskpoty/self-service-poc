package com.example.poc.application.port.in.web.account;

/**
 * UseCase or Service interface.
 * Represents the Web port through which the web adapter communicate with the web adapter
 */
public interface AccountUseCase {

    /**
     * Create a new Account
     * @param accountDTO
     * @return an AccountDTO
     */
    AccountDTO create(AccountDTO accountDTO);

    /**
     * Get an Account
     * @param id of the account
     * @return AccountDTO
     */
    AccountDTO getById(String id);

}
