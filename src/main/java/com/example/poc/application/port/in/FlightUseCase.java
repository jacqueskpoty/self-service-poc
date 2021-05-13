package com.example.poc.application.port.in;

import com.example.poc.application.port.dto.FlightDto;

public interface FlightUseCase {

    FlightDto addFlight(String id, FlightDto flightDto);
}
