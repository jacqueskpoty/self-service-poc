package com.example.poc.adapter.out.persistence.mapper;

import com.example.poc.adapter.out.persistence.model.AccountDocument;
import com.example.poc.domain.Account;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(uses={AssetDocumentMapper.class, FlightDocumentMapper.class},
        unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountDocumentMapper {

    AccountDocumentMapper INSTANCE = Mappers.getMapper(AccountDocumentMapper.class);

    AccountDocument toDocument(Account account);
    Account toDomain(AccountDocument accountDocument);

}
