package com.example.poc.domain;

import com.example.poc.application.port.in.web.asset.AssetDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;


@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@SuperBuilder
public class Asset extends BaseDomain{

    @Builder.Default
    private ObjectId _id = ObjectId.get();
    @JsonIgnore
    private MultipartFile file;
    @JsonIgnore
    private String location;
    private Long fileSize;
    private Long count;
    private Long consumption;
    private String name;
    private AssetType type;

    public String getStringId(){
        return _id.toString();
    }

    public ObjectId get_id(){
        return _id;
    }

    public Asset(MultipartFile file, String accountId, String assetType) throws InvalidAssetException {
        if(!validateAssetFile()) throw new InvalidAssetException("File is not accepted");
        this.file = file;
        this.type = AssetType.parse(assetType);
        this.location = accountId+"/"+assetType.toLowerCase()+"/"+file.getOriginalFilename();
        this.name = assetType;
    }

    public String getLocation() {
        return location;
    }

    public boolean validateAssetFile() throws InvalidAssetException {
        if(file.isEmpty()) throw new InvalidAssetException("File is Empty");
        if(file.getSize()>5000) throw new InvalidAssetException("File is too big");
        count =fileLineCount();
        if(count>1000000) throw new InvalidAssetException("Too many lines in the file");
        //OTHER BUSINESS LOGIC VERIFICATIONS RELATED TO THE ASSET FILE
        return true;
    }

    protected Long fileLineCount() throws InvalidAssetException{
        try {
            return new BufferedReader(new InputStreamReader(file.getInputStream())).lines().count();
        } catch (Exception e) {
            throw new InvalidAssetException("Failed to count file lines");
        }
    }

    public AssetDTO getAssetDTO(){
        return AssetDTO.builder().id(_id)
                .fileSize(fileSize)
                .count(count)
                .consumption(consumption)
                .name(name)
                .type(type.getAssetType())
                .build();
    }

    public class InvalidAssetException extends Exception{
        public InvalidAssetException(String message){
            super(message);
        }
    }
}
