package com.example.poc.multiversion.v2.application.port.in.web.flight;


public interface AddFlightUseCase {
    FlightDTO addFlight(String id, FlightDTO flightDTO);
}
