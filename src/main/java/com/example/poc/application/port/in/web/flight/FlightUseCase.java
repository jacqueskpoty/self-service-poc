package com.example.poc.application.port.in.web.flight;

/**
 * UseCase or Service interface.
 * Represents the Web port through which the web adapter communicate with the web adapter
 */
public interface FlightUseCase {
    /**
     * Adds a flight to the Account which's Account ID is provided
     * @param id AccountID of the account the flight belongs to
     * @param flightDTO sent by the web representing the flight
     * @return FlightDTO
     */
    FlightDTO addFlight(String id, FlightDTO flightDTO);
}
