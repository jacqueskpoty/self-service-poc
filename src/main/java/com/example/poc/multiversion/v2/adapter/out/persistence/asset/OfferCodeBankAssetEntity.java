package com.example.poc.multiversion.v2.adapter.out.persistence.asset;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OfferCodeBankAssetEntity extends AssetEntity {
    private String type = "OFFERCODEBANK";
}
