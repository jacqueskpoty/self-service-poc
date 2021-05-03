package com.example.poc.application.port.in.web.Flight;

import com.example.poc.domain.Flight;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used as a transfer object between the application and the web in layer
 * Serves as communication object between the web adpater the web web port
 * Uses Lombok Annotation
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class FlightDTO {
    private ObjectId id;
    private String name;
    @Builder.Default
    private List<Flight.FlightSet> flightSets = new ArrayList<>();

    /**
     * Return a FLight domain of this DTO
     * Uses the builder to create a new Flight
     * @return A Flight domain
     */
    public Flight getFlight(){
        return Flight.builder()
                .id(id)
                .name(name)
                .flightSet(flightSets)
                .build();
    }
}
