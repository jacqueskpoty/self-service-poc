package com.example.poc.multiversion.v1.account;

import com.example.poc.multiversion.v1.flight.Flight;
import com.example.poc.multiversion.v1.AccountService;
import com.example.poc.multiversion.v1.asset.Asset;
import com.example.poc.multiversion.v1.manager.AssetType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("v1")
@RequiredArgsConstructor
class AccountServiceV1 implements AccountService {

    private final AccountRepository repository;

    public AccountDTO create(AccountDTO account) {
        return repository.save(account.getAccount()).toDTO();
    }

    public AccountDTO getById(String id) {
        return repository.findById(id)
            .map(Account::toDTO)
            .orElseGet(AccountDTO::new);
    }

    public Flight addFlight(String id, Flight flight) {
        return repository.findById(id)
            .map(account -> account.appendFlight(flight))
            .map(repository::save)
            .map(Account::getLatestFlight)
            .orElseThrow(() -> new RuntimeException());
    }

    public Asset addAsset(String id, Asset asset) {
        return repository.findById(id)
            .map(account -> account.appendAsset(asset))
            .map(repository::save)
            .map(Account::getLatestAsset)
            .orElseThrow(() -> new RuntimeException());
    }

    public Asset getAsset(String accountId, String assetId) {
        return repository.findById(accountId)
            .map(Account.getAsset(assetId))
            .orElseThrow(() -> new RuntimeException());
    }

    public List<Asset> getAssets(String accountId, AssetType type) {
        return repository.findById(accountId)
            .map(Account::getAssets)
            .map(assets -> assets
                .stream()
                .filter(asset -> asset.getType().equals(type))
                .collect(Collectors.toList())
            )
            .orElseThrow(() -> new RuntimeException());
    }
}


