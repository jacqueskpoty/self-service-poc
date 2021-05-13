package com.example.poc.application.service;

import com.example.poc.application.port.dto.AssetDto;
import com.example.poc.application.port.dto.AssetFileDto;
import com.example.poc.application.port.dtomapper.AssetDtoMapper;
import com.example.poc.application.port.in.AssetUseCase;
import com.example.poc.application.port.out.IOServicePort;
import com.example.poc.application.port.out.AccountPort;
import com.example.poc.domain.Account;
import com.example.poc.domain.Asset;
import com.example.poc.domain.AssetType;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class DefaultAssetUseCase implements AssetUseCase {

    private final AccountPort accountPort;
    private final IOServicePort ioServicePort;
    private final AssetDtoMapper assetDtoMapper = AssetDtoMapper.INSTANCE;

    @Override
    public AssetDto addAsset(String id, AssetDto assetDto) {
        return accountPort.getAccountById(id)
                .map(account -> account.appendAsset(assetDtoMapper.toDomain(assetDto)))
                .map(accountPort::saveAccount)
                .map(Account::getLatestAsset)
                .map(assetDtoMapper::toDto)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public AssetDto addAssetWithFile(AssetFileDto file, String accountId, String assetType) {
        try {
            Asset asset = AssetType.parse(assetType)
                          .getAsset()
                          .createAsset(file.getInputStream(),
                                       file.getName(),
                                       file.getSize(),
                                       accountId,
                                       assetType);

            ioServicePort.upload(file,asset.getLocation());
            return addAsset(accountId,assetDtoMapper.toDto(asset));
        } catch (Asset.InvalidAssetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AssetDto getAsset(String accountId, String assetId) {
        return accountPort.getAccountById(accountId)
                .map(Account.getAsset(assetId))
                .map(assetDtoMapper::toDto)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<AssetDto> getAssets(String accountId, String type) {
        return accountPort.getAccountById(accountId)
                .map(Account::getAssets)
                .map(assets -> assets
                        .stream()
                        .filter(asset -> asset.getType()
                                        .getAssetType()
                                        .equalsIgnoreCase(type))
                        .map(assetDtoMapper::toDto)
                        .collect(Collectors.toList())
                ).orElseThrow(RuntimeException::new);
    }


    @Override
    public AssetFileDto downloadAssetFile(String accountId, String assetId) {
        try {
            AssetDto assetDto = getAsset(accountId,assetId);
            return AssetFileDto.builder()
                                    .name(assetDto.getName())
                                    .fileBytes(ioServicePort.download(assetDto.getLocation())).build();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
