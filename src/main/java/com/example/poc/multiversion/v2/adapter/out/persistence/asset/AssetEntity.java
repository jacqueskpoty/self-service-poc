package com.example.poc.multiversion.v2.adapter.out.persistence.asset;

import com.example.poc.multiversion.v2.application.domain.AssetType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AssetEntity {
    private ObjectId _id;
    private String location;
    private Long fileSize;
    private Long count;
    private Long consumption;
    private String name;
    private String type;
    private LocalDateTime created;
    private LocalDateTime updated;
}
