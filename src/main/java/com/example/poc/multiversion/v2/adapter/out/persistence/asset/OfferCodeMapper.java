package com.example.poc.multiversion.v2.adapter.out.persistence.asset;

import com.example.poc.multiversion.v2.application.domain.Asset;
import com.example.poc.multiversion.v2.application.domain.OfferCodeBankAsset;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OfferCodeMapper {

    OfferCodeBankAssetEntity toDomain(OfferCodeBankAsset offerCodeBankAsset);
    OfferCodeBankAsset toEntity(OfferCodeBankAssetEntity asset);
}
