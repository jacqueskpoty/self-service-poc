package com.example.poc.adapter.out.persistence.account;


import com.example.poc.application.port.out.persistence.AccountPort;
import com.example.poc.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * This class implements the out persistence port of our application layer
 * The application layer uses this class to communicate with the database
 * This class implements the OUT Persistence port through which the application layer accesses the database
 */
@Component
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements AccountPort {

    /**
     * The Account repository that will be used to perform queries on the database
     */
    private final AccountDocumentRepository accountDocumentRepository;
    /**
     * The account Mapper that maps an accountDocument to an account high level domain and vice versa
     * We use this because the application layer uses the account high level domain to perform reauest
     * The idea is to make sure that the application layer does not depends on the AccountDocument
     */
    private final AccountMapper accountMapper;

    /**
     * This function implements the getAccountByID of the OUT persistence port.
     * It queries an accountDocument from the database and maps the result into Account domain and returns it
     * @param id takes the account Id in parameter
     * @return an Optional value of the account queried.
     * The return value is mapped back to the Account because the application layer only knows the
     * account domain and not the accountDocument
     */
    @Override
    public Optional<Account> getAccountById(String id) {
        return Optional.of
                (accountMapper.toDomain
                        (accountDocumentRepository.findById(id).get()));
    }

    /**
     * Saves an account into the database
     * @param account Takes an account domain
     * @return and Account domain
     */
    @Override
    public Account saveAccount(Account account) {
        return accountMapper.toDomain
                (accountDocumentRepository.save
                        (accountMapper.toDocument(account)));
    }
}
