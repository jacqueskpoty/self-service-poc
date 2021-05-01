package com.example.poc.multiversion.v2.adapter.out.persistence.flight;

import com.example.poc.multiversion.v2.application.domain.Flight;
import com.example.poc.multiversion.v2.application.domain.FlightSet;
import org.mapstruct.Mapper;

@Mapper(uses = FlightMapper.FlightSetMapper.class, componentModel = "spring")
public interface FlightMapper {

    FlightEntity toEntity(Flight flight);
    Flight toDomain(FlightEntity flightEntity);

    @Mapper(componentModel = "spring")
    interface FlightSetMapper{
        FlightEntity.FlightSetEntity toEntity(FlightSet flightSet);
        FlightSet toDomain(FlightEntity.FlightSetEntity flightSetEntity);
    }
}

