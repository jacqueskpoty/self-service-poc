package com.example.poc.adapter.out.persistence.mapper;

import com.example.poc.adapter.out.persistence.model.AssetDocument;
import com.example.poc.domain.Asset;
import com.example.poc.domain.AssetType;
import com.example.poc.domain.OfferCodeBankAsset;
import com.example.poc.domain.ZipCodeBankAsset;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface AssetDocumentMapper {

    AssetDocumentMapper INSTANCE = Mappers.getMapper(AssetDocumentMapper.class);

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
