package com.example.poc.multiversion.v2.application.port.in.web.asset;


import com.example.poc.multiversion.v2.application.domain.Asset;
import com.example.poc.multiversion.v2.application.domain.AssetType;
import com.example.poc.multiversion.v2.application.domain.OfferCodeBankAsset;
import com.example.poc.multiversion.v2.application.domain.ZipCodeBankAsset;
import com.example.poc.multiversion.v2.common.SelfValidating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AssetDTO extends SelfValidating<AssetDTO> {

    private ObjectId id;
    private String location;
    private Long fileSize;
    private Long count;
    private Long consumption;
    private String name;
    private String type;

    public Asset getAsset(){
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
                        .type(AssetType.ZIP)
                        .build();
            case OFFERCODEBANK:
                return OfferCodeBankAsset.builder()
                        ._id(id)
                        .location(location)
                        .fileSize(fileSize)
                        .count(count)
                        .consumption(consumption)
                        .name(name)
                        .type(AssetType.OFFERCODEBANK)
                        .build();
            default:
                return null;
        }
    }
}
