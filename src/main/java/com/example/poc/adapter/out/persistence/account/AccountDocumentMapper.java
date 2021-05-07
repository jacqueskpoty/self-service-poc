package com.example.poc.adapter.out.persistence.account;

import com.example.poc.adapter.out.persistence.asset.AssetDocumentMapper;
import com.example.poc.adapter.out.persistence.flight.FlightDocumentMapper;
import com.example.poc.application.port.in.web.asset.AssetDTOMapper;
import com.example.poc.domain.Account;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Interface used to map the AccountDocument to the Account domain
 * The Account domain is a high level business logic model that has no dependency on anything
 * In order to understand the Mapper interface technology, learn about MapStruct on
 * https://mapstruct.org/
 */
@Mapper(uses={AssetDocumentMapper.class, FlightDocumentMapper.class},
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountDocumentMapper {

    AccountDocument toDocument(Account account);

    Account toDomain(AccountDocument accountDocument);

}
