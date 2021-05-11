package com.example.poc.adapter.out.persistence.mapper;

import com.example.poc.adapter.out.persistence.model.FlightDocument;
import com.example.poc.domain.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FlightSetDocumentMapper {

    FlightSetDocumentMapper INSTANCE = Mappers.getMapper(FlightSetDocumentMapper.class);

    FlightDocument.FlightSetDocument toDomain(Flight.FlightSet flightSet);

    Flight.FlightSet toDomain(FlightDocument.FlightSetDocument flightSetDocument);
}
