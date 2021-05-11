package com.example.poc.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@SuperBuilder
public class ZipCodeBankAsset extends Asset{

    @Builder.Default
    private AssetType assetType = AssetType.ZIP;

    public boolean validateAssetFile() throws InvalidAssetException {
        if(file.isEmpty()) throw new InvalidAssetException("File is Empty");
        if(file.getSize()>5000) throw new InvalidAssetException("File is too big");
        if(fileLineCount()>1000000) throw new InvalidAssetException("Too many lines in the file");
        return true;
    }
}
