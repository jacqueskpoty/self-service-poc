package com.example.poc.multiversion;

import com.example.poc.multiversion.v1.flight.Flight;
import com.example.poc.multiversion.v1.account.AccountDTO;
import com.example.poc.multiversion.v1.asset.Asset;
import com.example.poc.multiversion.v1.manager.AssetType;

import java.util.List;

public interface AccountService {
    AccountDTO create(AccountDTO account);
    AccountDTO getById(String id);
    Flight addFlight(String id, Flight flight);
    Asset addAsset(String id, Asset asset);
    Asset getAsset(String accountId, String assetId);
    List<Asset> getAssets(String accountId, AssetType type);
}
