package com.example.poc.application.port.in.web.asset;

import com.example.poc.domain.Asset;
import com.example.poc.domain.AssetType;
import com.example.poc.domain.OfferCodeBankAsset;
import com.example.poc.domain.ZipCodeBankAsset;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ObjectFactory;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AssetDTOMapper {
    @Mapping(source="_id", target = "id")
    @Mapping(source = "asset.type",target = "type")
    AssetDTO toDto(Asset asset);
    @Mapping(source="id", target = "_id")
    @Mapping(source = "assetDTO.type",target = "type")
    ZipCodeBankAsset toDomain(AssetDTO assetDTO);

    @ObjectFactory
    default Asset createAsset(AssetDTO assetDTO) {
        AssetType assetType = AssetType.parse(assetDTO.getType());
        switch (assetType){
            case OFFERCODEBANK:
                return new OfferCodeBankAsset();
            case ZIP:
                return new ZipCodeBankAsset();
        }
        return null;
    }
}