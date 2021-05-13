package com.example.poc.application.port.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode(callSuper = false)
public class AssetDto {

    private ObjectId id;
    private String location;
    private Long fileSize;
    private Long count;
    private Long consumption;
    private String name;
    private String type;
}
