package com.example.poc.adapter.in.web.mapper;

import com.example.poc.adapter.in.web.dto.FlightDTO;
import com.example.poc.domain.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FlightDTOMapper {

    FlightDTOMapper INSTANCE = Mappers.getMapper(FlightDTOMapper.class);

    FlightDTO toDto(Flight flight);
    Flight toDomain(FlightDTO flightDTO);
}

