package com.example.poc.multiversion.v2.application.service;

import com.example.poc.multiversion.v2.application.domain.Account;
import com.example.poc.multiversion.v2.application.domain.Asset;
import com.example.poc.multiversion.v2.application.port.in.web.asset.AssetDTO;
import com.example.poc.multiversion.v2.application.port.in.web.asset.GetAssetUseCase;
import com.example.poc.multiversion.v2.application.port.out.persistence.account.LoadAccountPort;
import com.example.poc.multiversion.v2.application.port.out.persistence.account.SaveAccountPort;
import com.example.poc.multiversion.v2.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
class GetAssetService implements GetAssetUseCase {

    private final LoadAccountPort loadAccountPort;

    @Override
    public AssetDTO getAsset(String accountId, String assetId) {
        return loadAccountPort.getAccountById(accountId)
                .map(Account.getAsset(assetId))
                .map(Asset::getAssetDTO)
                .orElseThrow(RuntimeException::new);
    }
}
