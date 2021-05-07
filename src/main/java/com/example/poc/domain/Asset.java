package com.example.poc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@SuperBuilder
public abstract class Asset extends BaseDomain{

    @Builder.Default
    protected ObjectId _id = ObjectId.get();
    @JsonIgnore
    protected MultipartFile file;
    @JsonIgnore
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



    public Asset createAsset(MultipartFile file, String accountId, String assetType) throws InvalidAssetException {
        if(!validateAssetFile()) throw new InvalidAssetException("File is not accepted");
        this.file = file;
        this.count = fileLineCount();
        this.type = AssetType.parse(assetType);
        this.location = accountId+"/"+assetType.toLowerCase()+"/"+file.getOriginalFilename();
        this.name = assetType;
        return this;
    }

    public abstract boolean validateAssetFile() throws InvalidAssetException;

    public String getLocation() {
        return location;
    }

    protected Long fileLineCount() throws InvalidAssetException{
        try {
            return new BufferedReader(new InputStreamReader(file.getInputStream())).lines().count();
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
