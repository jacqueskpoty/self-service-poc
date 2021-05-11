package com.example.poc.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * This is the center of the whole application
 * It represents the business logic of the whole application
 * Every business logic validation has to be done here.
 * This class in particular only deals with ACCOUNT domain business logic
 */
@Getter
@ToString
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Account extends BaseDomain{

    @Id
    private String id;
    private String name;

    @Builder.Default
    private List<Flight> flights = new ArrayList<>();

    @Builder.Default
    private List<Asset> assets = new ArrayList<>();


    public Account appendFlight(Flight flight) {
        flights.add(flight);
        return this;
    }


    public Account appendAsset(Asset asset) {
        assets.add(asset);
        return this;
    }


    public Asset getLatestAsset() {
        return assets
                .stream()
                .max(Comparator.comparing(Asset::getCreated))
                .get();
    }


    public Flight getLatestFlight() {
        return flights
                .stream()
                .max(Comparator.comparing(Flight::getCreated))
                .get();
    }


    public static Function<Account, Asset> getAsset(String assetId) {
        return (Account account) -> account.getAssets()
                .stream()
                .filter(asset -> asset.get_id().equals(assetId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException());

    }
}
