package com.example.poc.adapter.in.web.controller;

import com.example.poc.adapter.in.web.dto.FlightDTO;
import com.example.poc.adapter.in.web.mapper.FlightDTOMapper;
import com.example.poc.application.port.in.web.usecase.FlightUseCase;
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

    private final FlightDTOMapper flightDTOMapper;

    @PostMapping("/account/{id}/flight")
    public ResponseEntity createFlight(@PathVariable("id") String id,@RequestBody FlightDTO flightDTO) {
        return new ResponseEntity(
                    flightuseCase.addFlight(id, flightDTOMapper.toDomain(flightDTO))
                , HttpStatus.CREATED);
    }

}
