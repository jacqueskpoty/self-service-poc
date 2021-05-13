package com.example.poc.application.port.dtomapper;

import com.example.poc.application.port.dto.AccountDto;
import com.example.poc.domain.Account;
import org.mapstruct.Mapper;

import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {AssetDtoMapper.class, FlightDtoMapper.class},
        unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountDtoMapper {

    AccountDtoMapper INSTANCE = Mappers.getMapper( AccountDtoMapper.class );

    AccountDto toDto(Account account);
    Account toDomain(AccountDto accountDTO);
}
