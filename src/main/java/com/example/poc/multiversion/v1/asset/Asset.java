package com.example.poc.multiversion.v1.asset;


import com.example.poc.multiversion.v1.manager.AssetType;

import java.time.LocalDateTime;

public interface Asset {
    LocalDateTime getCreated();
    String get_id();
    String getLocation();
    String getName();
    AssetType getType();
}
