package com.example.poc.multiversion.v2.adapter.out.persistence.account;

import com.example.poc.multiversion.v2.adapter.out.persistence.asset.OfferCodeBankAssetEntity;
import com.example.poc.multiversion.v2.adapter.out.persistence.flight.FlightEntity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "accounts")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class AccountEntity {
    private String id;
    private String name;
    private LocalDateTime created;
    private LocalDateTime updated;
    private List<FlightEntity> flights = new ArrayList<>();
    private List<OfferCodeBankAssetEntity> assets = new ArrayList<>();
}
