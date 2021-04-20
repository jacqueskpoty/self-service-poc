package com.example.poc.multiversion.v1.manager;

import com.example.poc.multiversion.v1.asset.Asset;
import org.springframework.web.multipart.MultipartFile;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;


public interface AssetManager {
    AssetManager manage(MultipartFile file);
    AssetManager validate();
    AssetManager upload(BiConsumer<MultipartFile, String> consumer);
    AssetManager root(String root);
    Asset consume(BiFunction<String, Asset, Asset> mapper);
}
