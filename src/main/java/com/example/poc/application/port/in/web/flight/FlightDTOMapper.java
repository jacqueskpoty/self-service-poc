package com.example.poc.application.port.in.web.flight;

import com.example.poc.domain.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FlightDTOMapper {

    FlightDTO toDto(Flight flight);
    Flight toDomain(FlightDTO flightDTO);
}

