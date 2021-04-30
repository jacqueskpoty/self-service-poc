package com.example.poc.multiversion.v2.application.port.in.web.asset;

public interface GetAssetUseCase {
    AssetDTO getAsset(String accountId, String assetId);
}
