package com.example.poc.multiversion.v2.application.service;

import com.example.poc.multiversion.v2.application.domain.Account;
import com.example.poc.multiversion.v2.application.port.in.web.asset.AssetDTO;
import com.example.poc.multiversion.v2.application.port.in.web.asset.GetAssetListUseCase;
import com.example.poc.multiversion.v2.application.port.out.persistence.LoadAccountPort;
import com.example.poc.multiversion.v2.common.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
class GetAssetListService implements GetAssetListUseCase {

    private final LoadAccountPort loadAccountPort;

    @Override
    public List<AssetDTO> getAssets(String accountId, String type) {
        return loadAccountPort.getAccountById(accountId)
                .map(Account::getAssets)
                .map(assets -> assets
                        .stream()
                        .filter(asset -> asset.getType().getAssetType().equalsIgnoreCase(type))
                        .map(asset -> asset.getAssetDTO())
                        .collect(Collectors.toList())
                ).orElseThrow(RuntimeException::new);
    }
}
