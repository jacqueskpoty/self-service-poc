package com.example.poc.application.port.in;

import com.example.poc.application.port.dto.AssetDto;
import com.example.poc.application.port.dto.AssetFileDto;

import java.util.List;


public interface AssetUseCase {

    AssetDto addAsset(String id, AssetDto assetDto);

    AssetDto addAssetWithFile(AssetFileDto assetFileDto, String accountId, String assetType);

    AssetDto getAsset(String accountId, String assetId);

    List<AssetDto> getAssets(String accountId, String type);

    AssetFileDto downloadAssetFile(String accountId, String assetId);
}
