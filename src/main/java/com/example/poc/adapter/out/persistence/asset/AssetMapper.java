package com.example.poc.adapter.out.persistence.asset;

import com.example.poc.domain.Asset;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AssetMapper {

    AssetDocument toDocument(Asset asset);

    Asset toDomain(AssetDocument assetDocument);
}
