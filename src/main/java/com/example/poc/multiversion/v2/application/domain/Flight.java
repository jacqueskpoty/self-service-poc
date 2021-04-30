package com.example.poc.multiversion.v2.application.domain;

import com.example.poc.multiversion.v2.application.port.in.web.flight.FlightDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@SuperBuilder
public class Flight extends BaseDomain{
    @Builder.Default
    private ObjectId _id = ObjectId.get();
    private String name;

    @Builder.Default
    private List<FlightSet> flightSet = new ArrayList<>();

    public FlightDTO getFlightDTO(){
        return FlightDTO.builder()
                .id(_id)
                .name(name)
                .flightSet(flightSet)
                .build();
    }
}
