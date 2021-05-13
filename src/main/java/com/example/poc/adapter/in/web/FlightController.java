package com.example.poc.adapter.in.web;

import com.example.poc.application.port.dto.FlightDto;
import com.example.poc.application.port.in.FlightUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "api")
@RequiredArgsConstructor
public class FlightController {

    private final FlightUseCase flightuseCase;

    @PostMapping("/account/{id}/flight")
    public ResponseEntity createFlight(@PathVariable("id") String id,@RequestBody FlightDto flightDTO) {
        return new ResponseEntity(
                    flightuseCase.addFlight(id,flightDTO)
                    , HttpStatus.CREATED);
    }

}
