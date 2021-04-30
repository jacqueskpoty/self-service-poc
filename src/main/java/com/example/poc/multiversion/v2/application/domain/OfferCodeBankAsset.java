package com.example.poc.multiversion.v2.application.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class OfferCodeBankAsset extends Asset{

    @Builder.Default
    private AssetType type = AssetType.OFFERCODEBANK;

}
