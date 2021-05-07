package com.example.poc.application.port.in.web.asset;

import com.example.poc.domain.Asset;
import com.example.poc.domain.AssetType;
import com.example.poc.domain.OfferCodeBankAsset;
import com.example.poc.domain.ZipCodeBankAsset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;

/**
 * Data transfer object used by the web
 * Serves as communication object between the web adpater the web web port
 * Uses Lombok Annotation
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class AssetDTO {

    private ObjectId id;
    private String location;
    private Long fileSize;
    private Long count;
    private Long consumption;
    private String name;
    private String type;

    /**
     * Return an Asset domain of this DTO
     * Uses the builder to create a new Asset
     * @return an Asset
     */
    public Asset getAsset() {
        AssetType assetType = AssetType.parse(type);
        switch (assetType){
            case ZIP:
                return ZipCodeBankAsset.builder()
                        ._id(id)
                        .location(location)
                        .fileSize(fileSize)
                        .count(count)
                        .consumption(consumption)
                        .name(name)
                        .type(AssetType.parse(type))
                        .build();
            case OFFERCODEBANK:
                return OfferCodeBankAsset.builder()
                        ._id(id)
                        .location(location)
                        .fileSize(fileSize)
                        .count(count)
                        .consumption(consumption)
                        .name(name)
                        .type(AssetType.parse(type))
                        .build();
        }
        return null;
    }
}
