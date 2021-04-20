package com.example.poc.multiversion.v1.manager;

import com.example.poc.multiversion.v1.asset.Asset;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

@Slf4j
public class BaseManager implements AssetManager {
    protected String assetName;
    protected MultipartFile file;
    protected String root;

    public BaseManager(String assetName) {
        this.assetName = assetName;
    }

    public AssetManager manage(MultipartFile file) {
        this.file = file;
        return this;
    }

    public AssetManager root(String root) {
        this.root = root;
        return this;
    }

    public AssetManager upload(BiConsumer<MultipartFile, String> uploadConsumer) {
        if(root == null) { throw new RuntimeException("No file root found"); }
        uploadConsumer.accept(file, getLocation());
        return this;
    }

    protected String getLocation() {
        return root+"/"+assetName+"/"+file.getOriginalFilename();
    }

    public AssetManager validate() {
        log.info("Default unimplemented validator: file is valid");
        return this;
    }

    public Asset consume(BiFunction<String, Asset, Asset> mapper) {
        log.info("Default unimplemented consumer: nothing is consumed");
        return null;
    }

    protected Long fileLineCount() {
        try {
            return new BufferedReader(new InputStreamReader(file.getInputStream())).lines().count();
        } catch (Exception e) {
            throw new RuntimeException("Failed to count file lines");
        }
    }

}
