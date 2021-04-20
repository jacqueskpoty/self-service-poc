package com.example.poc.multiversion.v1.manager;

import com.example.poc.multiversion.v1.asset.Asset;
import com.example.poc.multiversion.v1.asset.ZipCodeBankAsset;
import lombok.extern.slf4j.Slf4j;
import java.util.function.BiFunction;

@Slf4j
public class ZipCodeManager extends BaseManager implements AssetManager {

    public ZipCodeManager(String assetName) {
        super(assetName);
    }

    public Asset consume(BiFunction<String, Asset, Asset> mapper) {
        return mapper.apply(
            root,
            ZipCodeBankAsset.builder()
                .location(getLocation())
                .fileSize(file.getSize())
                .count(fileLineCount())
                .name(file.getOriginalFilename())
                .build()
        );
    }
}
