package com.example.poc.multiversion.v2.adapter.out.persistence.account;

import com.example.poc.multiversion.v2.adapter.out.persistence.asset.OfferCodeMapper;
import com.example.poc.multiversion.v2.adapter.out.persistence.asset.ZipCodeMapper;
import com.example.poc.multiversion.v2.adapter.out.persistence.flight.FlightMapper;
import com.example.poc.multiversion.v2.application.domain.Account;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = {FlightMapper.class, ZipCodeMapper.class, OfferCodeMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE
        ,componentModel = "spring")
public interface AccountMapper {

    AccountEntity toEntity(Account account);

    Account toDomain(AccountEntity accountEntity);
}
