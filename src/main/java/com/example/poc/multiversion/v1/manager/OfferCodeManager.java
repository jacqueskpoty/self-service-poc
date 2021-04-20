package com.example.poc.multiversion.v1.manager;

import com.example.poc.multiversion.v1.asset.Asset;
import com.example.poc.multiversion.v1.asset.OfferCodeBankAsset;

import java.util.function.BiFunction;

public class OfferCodeManager extends BaseManager implements AssetManager {
    public OfferCodeManager(String assetName) {
        super(assetName);
    }


    public Asset consume(BiFunction<String, Asset, Asset> mapper) {
        return mapper.apply(
            root,
            OfferCodeBankAsset.builder()
                .location(getLocation())
                .fileSize(file.getSize())
                .count(fileLineCount())
                .consumption(0L)
                .name(file.getOriginalFilename())
                .build()
        );
    }

}
