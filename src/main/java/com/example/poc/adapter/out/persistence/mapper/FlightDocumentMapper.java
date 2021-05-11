package com.example.poc.adapter.out.persistence.mapper;

import com.example.poc.adapter.out.persistence.model.FlightDocument;
import com.example.poc.domain.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(uses={FlightSetDocumentMapper.class},
        unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FlightDocumentMapper {

    FlightDocumentMapper INSTANCE = Mappers.getMapper(FlightDocumentMapper.class);

    FlightDocument toDocument(Flight flight);

    Flight toDomain(FlightDocument flightDocument);
}

