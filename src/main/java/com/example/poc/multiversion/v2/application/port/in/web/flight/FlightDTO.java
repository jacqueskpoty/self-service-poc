package com.example.poc.multiversion.v2.application.port.in.web.flight;

import com.example.poc.multiversion.v2.application.domain.Flight;
import com.example.poc.multiversion.v2.application.domain.FlightSet;
import com.example.poc.multiversion.v2.common.SelfValidating;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class FlightDTO extends SelfValidating<FlightDTO> {
    private ObjectId id;
    private String name;
    @Builder.Default
    private List<FlightSet> flightSet = new ArrayList<>();

    public Flight getFlight(){
        return Flight.builder()
                ._id(id)
                .name(name)
                .flightSet(flightSet)
                .build();
    }
}
