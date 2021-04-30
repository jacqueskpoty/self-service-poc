package com.example.poc.multiversion.v2.application.service;

import com.example.poc.multiversion.v2.application.domain.Account;
import com.example.poc.multiversion.v2.application.domain.Flight;
import com.example.poc.multiversion.v2.application.port.in.web.flight.AddFlightUseCase;
import com.example.poc.multiversion.v2.application.port.in.web.flight.FlightDTO;
import com.example.poc.multiversion.v2.application.port.out.persistence.account.LoadAccountPort;
import com.example.poc.multiversion.v2.application.port.out.persistence.account.SaveAccountPort;
import com.example.poc.multiversion.v2.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
class AddFlightService implements AddFlightUseCase {

    private final LoadAccountPort loadAccountPort;
    private final SaveAccountPort saveAccountPort;

    @Override
    public FlightDTO addFlight(String id, FlightDTO flightDTO) {
        return loadAccountPort.getAccountById(id)
                .map(account -> account.appendFlight(flightDTO.getFlight()))
                .map(saveAccountPort::saveAccount)
                .map(Account::getLatestFlight)
                .map(Flight::getFlightDTO)
                .orElseThrow(RuntimeException::new);
    }
}
