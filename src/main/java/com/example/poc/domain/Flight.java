package com.example.poc.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents Flight Domain, used to manipulate and validate business logics related to Flight
 * This class just as any other business logic model should have no dependencies
 * This class uses Lombok annotations
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@SuperBuilder
public class Flight extends BaseDomain{

    @Builder.Default
    private ObjectId id = ObjectId.get();
    private String name;

    @Builder.Default
    private List<FlightSet> flightSet = new ArrayList<>();


    /**
     * FlightSet static subclass representing some features related to a flight
     */
    @Data
    @NoArgsConstructor
    public static class FlightSet {
        private String offerCodeBank;
        private String strategyId;
        private String creativeId;
        private String zipCodeBankId;
    }
}
