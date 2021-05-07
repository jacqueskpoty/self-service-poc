package com.example.poc.adapter.out.persistence.asset;

import com.example.poc.domain.Asset;
import com.example.poc.domain.AssetType;
import com.example.poc.domain.OfferCodeBankAsset;
import com.example.poc.domain.ZipCodeBankAsset;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface AssetDocumentMapper {

    @Mapping(source = "asset.type",target = "type")
    AssetDocument toDocument(Asset asset);

    @Mapping(source = "assetDocument.type",target = "type")
    Asset toDomain(AssetDocument assetDocument);

    @ObjectFactory
    default Asset createAsset(AssetDocument assetDocument) {
        AssetType assetType = AssetType.parse(assetDocument.getType());
        switch (assetType){
            case OFFERCODEBANK:
                return new OfferCodeBankAsset();
            case ZIP:
                return new ZipCodeBankAsset();
        }
        return null;
    }

}
