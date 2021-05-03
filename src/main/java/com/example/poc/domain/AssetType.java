package com.example.poc.domain;

/**
 * AssetType class, representing the Asset Types
 */
public enum AssetType {
    OFFERCODEBANK("OFFERCODEBANK"),
    ZIP("ZIP");

    private String assetType;

    AssetType(String assetType) {
        this.assetType = assetType;
    }

    /**
     * Get the AssetType's code
     * @return the AssetType's type in String
     */
    public String getAssetType() {
        return assetType;
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
