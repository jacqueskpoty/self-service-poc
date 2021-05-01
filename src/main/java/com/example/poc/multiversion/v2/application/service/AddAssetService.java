package com.example.poc.multiversion.v2.application.service;

import com.example.poc.multiversion.v2.application.domain.Account;
import com.example.poc.multiversion.v2.application.domain.Asset;
import com.example.poc.multiversion.v2.application.port.in.web.asset.AddAssetUseCase;
import com.example.poc.multiversion.v2.application.port.in.web.asset.AssetDTO;
import com.example.poc.multiversion.v2.application.port.out.persistence.LoadAccountPort;
import com.example.poc.multiversion.v2.application.port.out.persistence.SaveAccountPort;
import com.example.poc.multiversion.v2.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
class AddAssetService implements AddAssetUseCase {

    private final LoadAccountPort loadAccountPort;
    private final SaveAccountPort saveAccountPort;

    @Override
    public AssetDTO addAsset(String id, AssetDTO assetDTO) {
        return loadAccountPort.getAccountById(id)
                .map(account -> account.appendAsset(assetDTO.getAsset()))
                .map(saveAccountPort::saveAccount)
                .map(Account::getLatestAsset)
                .map(Asset::getAssetDTO)
                .orElseThrow(() -> new RuntimeException());
    }
}
