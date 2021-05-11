package com.example.poc.application.service;

import com.example.poc.application.port.in.web.usecase.FlightUseCase;
import com.example.poc.application.port.out.persistence.AccountPort;
import com.example.poc.domain.Account;
import com.example.poc.domain.Flight;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class FlightService implements FlightUseCase {

    private final AccountPort accountPort;

    @Override
    public Flight addFlight(String id, Flight flight) {
        return accountPort.getAccountById(id)
                .map(account -> account.appendFlight(flight))
                .map(accountPort::saveAccount)
                .map(Account::getLatestFlight)
                .orElseThrow(RuntimeException::new);
    }
}
