package com.example.poc.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


@Data

@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@SuperBuilder
public abstract class Asset extends BaseDomain{

    @Builder.Default
    protected ObjectId _id = ObjectId.get();
    protected InputStream inputStream;
    protected String location;
    protected Long fileSize;
    protected Long count;
    protected Long consumption;
    protected String name;
    protected AssetType type;

    public String getStringId(){
        return _id.toString();
    }

    public ObjectId get_id(){
        return _id;
    }



    public Asset createAsset(InputStream inputStream,
                             String fileName,
                             Long size,
                             String accountId,
                             String assetType) throws InvalidAssetException {
        if(!validateAssetFile()) throw new InvalidAssetException("File is not accepted");
        this.inputStream = inputStream;
        this.count = fileLineCount();
        this.fileSize = size;
        this.type = AssetType.parse(assetType);
        this.location = accountId+"/"+assetType.toLowerCase()+"/"+fileName;
        this.name = assetType;
        return this;
    }

    public abstract boolean validateAssetFile() throws InvalidAssetException;

    public String getLocation() {
        return location;
    }

    protected Long fileLineCount() throws InvalidAssetException{
        try {
            return new BufferedReader(new InputStreamReader(inputStream)).lines().count();
        } catch (Exception e) {
            throw new InvalidAssetException("Failed to count file lines");
        }
    }

    public class InvalidAssetException extends Exception{
        public InvalidAssetException(String message){
            super(message);
        }
    }
}
