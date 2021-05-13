package com.example.poc.application.service;

import com.example.poc.application.port.dto.FlightDto;
import com.example.poc.application.port.dtomapper.FlightDtoMapper;
import com.example.poc.application.port.in.FlightUseCase;
import com.example.poc.application.port.out.AccountPort;
import com.example.poc.domain.Account;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class DefaultFlightUseCase implements FlightUseCase {

    private final AccountPort accountPort;
    private final FlightDtoMapper flightDtoMapper = FlightDtoMapper.INSTANCE;

    @Override
    public FlightDto addFlight(String id, FlightDto flightDto) {
        return accountPort.getAccountById(id)
                .map(account -> account.appendFlight(flightDtoMapper.toDomain(flightDto)))
                .map(accountPort::saveAccount)
                .map(Account::getLatestFlight)
                .map(flightDtoMapper::toDto)
                .orElseThrow(RuntimeException::new);
    }
}
