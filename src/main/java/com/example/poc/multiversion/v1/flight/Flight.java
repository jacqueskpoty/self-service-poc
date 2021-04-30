package com.example.poc.multiversion.v1.flight;

import com.example.poc.multiversion.v1.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Flight extends BaseEntity {
    @Builder.Default
    private ObjectId _id = ObjectId.get();
    private String name;

    @Builder.Default
    private List<Flight.FlightSet> flightSet = new ArrayList<>();

    @Data
    @NoArgsConstructor
    static class FlightSet {
        private String offerCodeBank;
        private String strategyId;
        private String creativeId;
        private String zipCodeBankId;
    }
}
