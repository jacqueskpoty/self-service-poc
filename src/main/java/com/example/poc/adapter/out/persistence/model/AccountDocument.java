package com.example.poc.adapter.out.persistence.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Document(collection = "accounts")
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class AccountDocument {

    private String id;
    private String name;
    private LocalDateTime created;
    private LocalDateTime updated;
    private List<FlightDocument> flights = new ArrayList<>();
    private List<AssetDocument> assets = new ArrayList<>();


}
