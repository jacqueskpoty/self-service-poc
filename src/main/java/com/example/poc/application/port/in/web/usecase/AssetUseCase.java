package com.example.poc.application.port.in.web.usecase;

import com.example.poc.application.port.in.web.response.AssetFileResponse;
import com.example.poc.domain.Asset;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface AssetUseCase {


    Asset addAsset(String id, Asset assetDTO);


    Asset addAssetWithFile(MultipartFile file, String accountId, String assetType);


    Asset getAsset(String accountId, String assetId);


    List<Asset> getAssets(String accountId, String type);


    AssetFileResponse downloadAssetFile(String accountId, String assetId);
}
