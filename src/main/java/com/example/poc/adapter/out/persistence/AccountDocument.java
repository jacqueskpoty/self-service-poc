package com.example.poc.adapter.out.persistence;

import com.example.poc.domain.Asset;
import com.example.poc.domain.Flight;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represent the Account document that is used to create a document in Mongo DB
 * This class is part of our OUT Persistence Adapter. It only handle things related to the database
 * It partially depends on our application layer but not the other way around
 * Changing this class only affects the DB
 * This class holds no business logic and no business validations
 */
@Document(collection = "accounts")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class AccountDocument {

    private String id;
    private String name;
    private LocalDateTime created;
    private LocalDateTime updated;
    private List<Flight> flights = new ArrayList<>();
    private List<Asset> assets = new ArrayList<>();

}
