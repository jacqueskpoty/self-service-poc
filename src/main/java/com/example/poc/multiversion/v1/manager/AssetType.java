package com.example.poc.multiversion.v1.manager;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AssetType {
    OFFERCODEBANK("offercodebank", new OfferCodeManager("offercodebank")),
     ZIP("zip", new ZipCodeManager("zip"));
//    , CREATIVE("creative", ...)
//    , AUDIENCE("audience", ...)
//    , MARKER("marker", ...);

    @JsonValue
    public final String assetType;
    private AssetManager manager;

    AssetType(String assetType, AssetManager manager) {
        this.assetType = assetType;
        this.manager = manager;
    }

    public AssetManager manager() {
        return this.manager;
    }
}
