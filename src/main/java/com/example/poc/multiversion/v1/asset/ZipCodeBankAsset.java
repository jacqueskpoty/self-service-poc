package com.example.poc.multiversion.v1.asset;

import com.example.poc.multiversion.BaseEntity;
import com.example.poc.multiversion.v1.manager.AssetType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ZipCodeBankAsset extends BaseEntity implements Asset {
    @Builder.Default
    private ObjectId _id = ObjectId.get();
    @JsonIgnore
    private String location;
    private Long fileSize;
    private Long count;
    private String name;
    @Builder.Default
    private AssetType type = AssetType.ZIP;

    public String get_id() { return _id.toString(); }
}
