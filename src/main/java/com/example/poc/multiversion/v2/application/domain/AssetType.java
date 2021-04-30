package com.example.poc.multiversion.v2.application.domain;

import com.fasterxml.jackson.annotation.JsonValue;


public enum AssetType {

    OFFERCODEBANK("OFFERCODEBANK"),
    ZIP("ZIP"),
    DEFAULT("NONE");

    @JsonValue
    private final String assetType;

    AssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetType() {
        return assetType;
    }

    public static AssetType parse(String code){
        for(AssetType assetType : AssetType.values()){
            if(code.equalsIgnoreCase(assetType.assetType)){
                return assetType;
            }
        }
        return null;
    }
}
