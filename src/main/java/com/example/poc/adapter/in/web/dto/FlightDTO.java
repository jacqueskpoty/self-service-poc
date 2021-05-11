package com.example.poc.adapter.in.web.dto;

import com.example.poc.domain.Flight;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode(callSuper = false)
public class FlightDTO {
    private ObjectId id;
    private String name;
    @Builder.Default
    private List<Flight.FlightSet> flightSets = new ArrayList<>();

}
