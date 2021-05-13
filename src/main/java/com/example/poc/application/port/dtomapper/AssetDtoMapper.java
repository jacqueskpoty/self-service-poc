package com.example.poc.application.port.dtomapper;

import com.example.poc.application.port.dto.AssetDto;
import com.example.poc.domain.Asset;
import com.example.poc.domain.AssetType;
import com.example.poc.domain.OfferCodeBankAsset;
import com.example.poc.domain.ZipCodeBankAsset;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ObjectFactory;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AssetDtoMapper {

    AssetDtoMapper INSTANCE = Mappers.getMapper( AssetDtoMapper.class );

    @Mapping(source="_id", target = "id")
    @Mapping(source = "asset.type",target = "type")
    AssetDto toDto(Asset asset);
    @Mapping(source="id", target = "_id")
    @Mapping(source = "assetDTO.type",target = "type")
    ZipCodeBankAsset toDomain(AssetDto assetDTO);

    @ObjectFactory
    default Asset createAsset(AssetDto assetDTO) {
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