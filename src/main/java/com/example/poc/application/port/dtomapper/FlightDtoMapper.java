package com.example.poc.application.port.dtomapper;

import com.example.poc.application.port.dto.FlightDto;
import com.example.poc.domain.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FlightDtoMapper {

    FlightDtoMapper INSTANCE = Mappers.getMapper(FlightDtoMapper.class);

    FlightDto toDto(Flight flight);
    Flight toDomain(FlightDto flightDTO);
}

