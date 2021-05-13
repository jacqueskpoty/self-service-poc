package com.example.poc.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.InputStream;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@SuperBuilder
public class OfferCodeBankAsset extends Asset{

    @Builder.Default
    private AssetType assetType = AssetType.OFFERCODEBANK;

    public boolean validateAssetFile() throws InvalidAssetException {
        if(inputStream== InputStream.nullInputStream()) throw new InvalidAssetException("File is Empty");
        if(fileSize>5000) throw new InvalidAssetException("File is too big");
        if(fileLineCount()>1000000) throw new InvalidAssetException("Too many lines in the file");
        return true;
    }
}
