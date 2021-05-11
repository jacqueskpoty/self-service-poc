package com.example.poc.adapter.out.persistence.model;

import lombok.*;
import org.bson.types.ObjectId;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class AssetDocument {
    private ObjectId _id;
    private String location;
    private Long fileSize;
    private Long count;
    private Long consumption;
    private String name;
    private String type;
}
