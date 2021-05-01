package com.example.poc.multiversion.v2.adapter.out.persistence.asset;

import com.example.poc.multiversion.v2.application.domain.ZipCodeBankAsset;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ZipCodeMapper {

    ZipCodeBankAssetEntity toEntity(ZipCodeBankAsset zipCodeBankAsset);

    ZipCodeBankAsset toDomain(ZipCodeBankAssetEntity zipCodeBankAssetEntity);
}
