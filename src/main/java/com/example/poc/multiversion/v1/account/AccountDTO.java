package com.example.poc.multiversion.v1.account;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {
    private String id;
    private String name;
    private List<?> offerCodeBanks;

    @JsonIgnore public Account getAccount() {
        return Account
            .builder()
            .id(id)
            .name(name)
            .build();
    }
}
