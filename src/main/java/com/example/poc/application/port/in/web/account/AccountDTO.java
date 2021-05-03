package com.example.poc.application.port.in.web.account;

import com.example.poc.domain.Account;
import com.example.poc.domain.Asset;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Data transfer object used by the web
 * Serves as communication object between the web adpater the web web port
 * Uses Lombok Annotation
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
public class AccountDTO {
    @NotNull
    private String id;
    @NotNull
    private String name;
    @Builder.Default
    private List<Asset> assets = new ArrayList<>();

    /**
     * Return an account domain of this DTO
     * Uses the builder to create a new Account
     * @return An account domain
     */
    @JsonIgnore
    public Account getAccount() {
        return Account
                .builder()
                .id(id)
                .name(name)
                .build();
    }
}
