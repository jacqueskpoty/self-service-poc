package com.example.poc.application.port.in.web.account;

import com.example.poc.application.port.in.web.asset.AssetDTOMapper;
import com.example.poc.application.port.in.web.flight.FlightDTOMapper;
import com.example.poc.domain.Account;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = {AssetDTOMapper.class, FlightDTOMapper.class},
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountDTOMapper {

    AccountDTO toDto(Account account);
    Account toDomain(AccountDTO accountDTO);
}
