package com.example.poc.adapter.out.persistence.flight;

import com.example.poc.domain.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FlightSetMapper {

    FlightDocument.FlightSetDocument toDomain(Flight.FlightSet flightSet);

    Flight.FlightSet toDomain(FlightDocument.FlightSetDocument flightSetDocument);
}
