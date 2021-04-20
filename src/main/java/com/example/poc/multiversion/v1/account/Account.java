package com.example.poc.multiversion.v1.account;

import com.example.poc.multiversion.v1.flight.Flight;
import com.example.poc.multiversion.BaseEntity;
import com.example.poc.multiversion.v1.asset.Asset;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;
import java.util.function.Function;

@Slf4j
@Document(collection = "accounts")
@Getter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Account extends BaseEntity {
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

    public AccountDTO toDTO() {
        return AccountDTO
            .builder()
            .id(id)
            .name(name)
            .build();
    }
}
