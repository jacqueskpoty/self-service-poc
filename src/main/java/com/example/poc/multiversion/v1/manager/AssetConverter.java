package com.example.poc.multiversion.v1.manager;


import com.example.poc.multiversion.v1.manager.AssetType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AssetConverter implements Converter<String, AssetType> {
    public AssetType convert(String value) { return AssetType.valueOf(value.toUpperCase()); }
}

