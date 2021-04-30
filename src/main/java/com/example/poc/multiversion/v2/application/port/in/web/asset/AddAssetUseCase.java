package com.example.poc.multiversion.v2.application.port.in.web.asset;

public interface AddAssetUseCase {
    AssetDTO addAsset(String id, AssetDTO assetDTO);
}
