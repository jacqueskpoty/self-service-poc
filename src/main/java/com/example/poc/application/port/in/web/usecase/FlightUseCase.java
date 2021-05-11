package com.example.poc.application.port.in.web.usecase;

import com.example.poc.domain.Flight;

public interface FlightUseCase {

    Flight addFlight(String id, Flight flight);
}
