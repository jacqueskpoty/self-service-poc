package com.example.poc.adapter.in.web;

import com.example.poc.application.port.in.web.Flight.FlightDTO;
import com.example.poc.application.port.in.web.Flight.FlightUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class is a RestController using Lombok annotation to avoid boiler plate code
 * Hover the mouse on each of the annotation to see what they do
 * This class represents the Flight controller
 * It takes HTTP requests and forward them to the application module
 * It's an IN ADAPTER
 * */
@Slf4j
@RestController
@RequestMapping(path = "api")
@RequiredArgsConstructor
public class FlightController {

    /**
     * Initializing the application's IN PORT so as to consume service from the application
     * We communicate with the application through this port
     * Particularly requests related to a Flight(Business term)
     */
    private final FlightUseCase flightuseCase;

    /**
     * This is a Post request used to create a new flight
     * @param id The
     * @param flightDTO
     * @return
     */
    @PostMapping("/account/{id}/flight")
    public ResponseEntity createFlight(@PathVariable("id") String id,@RequestBody FlightDTO flightDTO) {
        return new ResponseEntity(flightuseCase.addFlight(id, flightDTO), HttpStatus.CREATED);
    }

}
