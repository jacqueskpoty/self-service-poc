package com.example.poc.domain;

import lombok.ToString;

/**
 * AssetType class, representing the Asset Types
 */
@ToString
public enum AssetType {
    OFFERCODEBANK("OFFERCODEBANK",new OfferCodeBankAsset()),
    ZIP("ZIP", new ZipCodeBankAsset());

    private String assetType;
    private Asset asset;

    AssetType(String assetType,Asset asset) {
        this.assetType = assetType;
    }


    public String getAssetType() {
        return assetType;
    }

    public Asset getAsset(){
        return asset;
    }

    /**
     * Create an AssetType based on a an assetType code given
     * @param code representing the assetType
     * @return AssetType Object
     */
    public static AssetType parse(String code){
        for(AssetType assetType : AssetType.values()){
            if(code.equalsIgnoreCase(assetType.assetType)){
                return assetType;
            }
        }
        return null;
    }
}
