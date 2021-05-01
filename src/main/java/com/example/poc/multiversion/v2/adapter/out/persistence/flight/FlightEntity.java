package com.example.poc.multiversion.v2.adapter.out.persistence.flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightEntity {
    private ObjectId _id;
    private String name;
    private LocalDateTime created;
    private LocalDateTime updated;
    private List<FlightSetEntity> flightSet = new ArrayList<>();

    @Data
    @NoArgsConstructor
    static class FlightSetEntity {
        private String offerCodeBank;
        private String strategyId;
        private String creativeId;
        private String zipCodeBankId;
    }
}
