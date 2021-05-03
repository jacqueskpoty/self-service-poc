package com.example.poc.application.service;

import com.example.poc.application.port.in.web.Flight.FlightDTO;
import com.example.poc.application.port.in.web.Flight.FlightUseCase;
import com.example.poc.application.port.out.persistence.AccountPort;
import com.example.poc.domain.Account;
import com.example.poc.domain.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Flight Service implementation, package private do not need to be directly accessed by any class
 * This class is part of the core of the application
 * It takes request coming through the PORT IN and forwards them to the PORT OUT
 * This class deals with use cases only related to the FLIGHT Domain
 * Uses Lombok Annotation
 */
@Service
@RequiredArgsConstructor
public class FlightService implements FlightUseCase {

    /**
     * Initializing the Account OUT Port as it's needed to make calls to the Persistence Adapter
     */
    private final AccountPort accountPort;

    /**
     * Takes Add Flight reauest from WEB IN Port and forwards in the the Persistence OUT PORT
     * @param id AccountID of the account the flight belongs to
     * @param flightDTO sent by the web representing the flight
     * @return FlightDTO object
     */
    @Override
    public FlightDTO addFlight(String id, FlightDTO flightDTO) {
        return accountPort.getAccountById(id)
                .map(account -> account.appendFlight(flightDTO.getFlight()))
                .map(accountPort::saveAccount)
                .map(Account::getLatestFlight)
                .map(Flight::getFlightDTO)
                .orElseThrow(RuntimeException::new);
    }
}
