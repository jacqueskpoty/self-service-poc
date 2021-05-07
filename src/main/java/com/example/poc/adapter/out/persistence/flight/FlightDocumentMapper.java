package com.example.poc.adapter.out.persistence.flight;

import com.example.poc.domain.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses={FlightSetDocumentMapper.class},
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FlightDocumentMapper {

    FlightDocument toDocument(Flight flight);

    Flight toDomain(FlightDocument flightDocument);
}

