package com.example.poc.adapter.out.persistence.model;

import lombok.*;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class FlightDocument {
    private ObjectId id;
    private String name;
    private List<FlightDocument.FlightSetDocument> flightSet;

    @Data
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper=false)
    public static class FlightSetDocument {
        private String offerCodeBank;
        private String strategyId;
        private String creativeId;
        private String zipCodeBankId;
    }
}
