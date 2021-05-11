package com.example.poc.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class AssetDTO {

    private ObjectId id;
    private String location;
    private Long fileSize;
    private Long count;
    private Long consumption;
    private String name;
    private String type;
}
