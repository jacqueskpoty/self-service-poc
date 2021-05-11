package com.example.poc.application.service;

import com.example.poc.application.port.in.web.response.AssetFileResponse;
import com.example.poc.application.port.in.web.usecase.AssetUseCase;
import com.example.poc.application.port.out.IO.IOServicePort;
import com.example.poc.application.port.out.persistence.AccountPort;
import com.example.poc.domain.Account;
import com.example.poc.domain.Asset;
import com.example.poc.domain.AssetType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class DefaultAssetUseCase implements AssetUseCase {

    private final AccountPort accountPort;

    private final IOServicePort ioServicePort;

    @Override
    public Asset addAsset(String id, Asset asset) {
        return accountPort.getAccountById(id)
                .map(account -> account.appendAsset(asset))
                .map(accountPort::saveAccount)
                .map(Account::getLatestAsset)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Asset addAssetWithFile(MultipartFile file, String accountId, String assetType) {
        try {
            Asset asset = AssetType.parse(assetType).getAsset().createAsset(file,accountId,assetType);
            ioServicePort.upload(file,asset.getLocation());
            return addAsset(accountId,asset);
        } catch (Asset.InvalidAssetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Asset getAsset(String accountId, String assetId) {
        return accountPort.getAccountById(accountId)
                .map(Account.getAsset(assetId))
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Asset> getAssets(String accountId, String type) {
        return accountPort.getAccountById(accountId)
                .map(Account::getAssets)
                .map(assets -> assets
                        .stream()
                        .filter(asset -> asset.getType().getAssetType().equalsIgnoreCase(type))
                        .collect(Collectors.toList())
                ).orElseThrow(RuntimeException::new);
    }


    @Override
    public AssetFileResponse downloadAssetFile(String accountId, String assetId) {
        try {
            Asset asset = getAsset(accountId,assetId);
            return AssetFileResponse.builder()
                                    .name(asset.getName())
                                    .fileBytes(ioServicePort.download(asset.getLocation())).build();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
