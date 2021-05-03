package com.example.poc.domain;

import com.example.poc.application.port.in.web.account.AccountDTO;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    /**
     * Add a Flight to this account
     * @param flight The Flight object to add
     * @return Account
     */
    public Account appendFlight(Flight flight) {
        flights.add(flight);
        return this;
    }

    /**
     * Add an Asset to this account
     * @param asset the Asset object to add
     * @return Account
     */
    public Account appendAsset(Asset asset) {
        assets.add(asset);
        return this;
    }

    /**
     * Get the latest Asset added to this account
     * @return Asset
     */
    public Asset getLatestAsset() {
        return assets
                .stream()
                .max(Comparator.comparing(Asset::getCreated))
                .get();
    }

    /**
     * Get the latest Flight added to this account
     * @return Flight
     */
    public Flight getLatestFlight() {
        return flights
                .stream()
                .max(Comparator.comparing(Flight::getCreated))
                .get();
    }

    /**
     * Get an asset belonging to this Account object
     * @param assetId of the asset we are targeting
     * @return a function that takes in an account and returns an asset
     * @IIIya Good Job on using this, I never used it before 👍
     */
    public static Function<Account, Asset> getAsset(String assetId) {
        return (Account account) -> account.getAssets()
                .stream()
                .filter(asset -> asset.get_id().equals(assetId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException());

    }

    /**
     * This is to get a DTO version of this object
     * This makes this class to depend on the WEB PORT IN and has to be removed
     * TODO remove this from here. Changing the DAO class should not change this class
     * 😊 I suggest we should use MapStruct to keep our business logic from unnecessary changes ✌️
     * @return
     */
    public AccountDTO toDTO() {
        return AccountDTO
                .builder()
                .id(id)
                .name(name)
                .build();
    }
}
