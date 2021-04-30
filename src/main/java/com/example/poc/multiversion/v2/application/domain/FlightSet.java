package com.example.poc.multiversion.v2.application.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FlightSet {
    private String offerCodeBank;
    private String strategyId;
    private String creativeId;
    private String zipCodeBankId;
}
