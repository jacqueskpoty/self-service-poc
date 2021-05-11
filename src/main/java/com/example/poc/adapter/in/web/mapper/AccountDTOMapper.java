package com.example.poc.adapter.in.web.mapper;

import com.example.poc.adapter.in.web.dto.AccountDTO;
import com.example.poc.domain.Account;
import org.mapstruct.Mapper;

import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {AssetDTOMapper.class, FlightDTOMapper.class},
        unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountDTOMapper {

    AccountDTOMapper INSTANCE = Mappers.getMapper( AccountDTOMapper.class );

    AccountDTO toDto(Account account);
    Account toDomain(AccountDTO accountDTO);
}
