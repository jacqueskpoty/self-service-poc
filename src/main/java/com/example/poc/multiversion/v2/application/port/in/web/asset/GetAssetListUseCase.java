package com.example.poc.multiversion.v2.application.port.in.web.asset;

import java.util.List;

public interface GetAssetListUseCase {

    List<AssetDTO> getAssets(String accountId, String type);

}

