package com.example.poc.adapter.out.persistence.flight;

import com.example.poc.domain.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses={FlightSetMapper.class},
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FlightMapper {

    FlightDocument toDocument(Flight flight);

    Flight toDomain(FlightDocument flightDocument);
}

