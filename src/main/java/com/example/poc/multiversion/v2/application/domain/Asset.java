package com.example.poc.multiversion.v2.application.domain;

import com.example.poc.multiversion.v2.application.port.in.web.asset.AssetDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@SuperBuilder
public abstract class Asset extends BaseDomain{

    @Builder.Default
    private ObjectId _id = ObjectId.get();
    @JsonIgnore
    private String location;
    private Long fileSize;
    private Long count;
    private Long consumption;
    private String name;
    @Builder.Default
    private AssetType type = AssetType.DEFAULT;

    public String get_id() { return _id.toString(); }

    public AssetDTO getAssetDTO(){
        return AssetDTO.builder().id(_id)
                .fileSize(fileSize)
                .count(count)
                .consumption(consumption)
                .name(name)
                .type(type.getAssetType())
                .build();
    }
}
