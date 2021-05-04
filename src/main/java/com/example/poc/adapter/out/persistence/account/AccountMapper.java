package com.example.poc.adapter.out.persistence.account;

import com.example.poc.adapter.out.persistence.account.AccountDocument;
import com.example.poc.adapter.out.persistence.asset.AssetMapper;
import com.example.poc.adapter.out.persistence.flight.FlightMapper;
import com.example.poc.adapter.out.persistence.flight.FlightSetMapper;
import com.example.poc.domain.Account;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Interface used to map the AccountDocument to the Account domain
 * The Account domain is a high level business logic model that has no dependency on anything
 * In order to understand the Mapper interface technology, learn about MapStruct on
 * https://mapstruct.org/
 */
@Mapper(uses={AssetMapper.class, FlightMapper.class},
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {
    /**
     * Mapping an account domain to an AccountDocument
     * @param account Takes an account high level domain in parameter
     * @return an AccountDocument mapped to the provided account
     */
    AccountDocument toDocument(Account account);

    /**
     * Mapping a high level account domain into the accountDocument
     * @param accountDocument Takes an accountDocument as param
     * @return an Account high level domain mapped to the accountDocument provvided
     */
    Account toDomain(AccountDocument accountDocument);
}
