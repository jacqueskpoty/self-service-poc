package com.example.poc.multiversion.v2.application.port.in.web.account;

import com.example.poc.multiversion.v2.application.domain.Account;
import com.example.poc.multiversion.v2.common.SelfValidating;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO extends SelfValidating<AccountDTO> {

    @NotNull
    private String id;
    @NotNull
    private String name;
    private List<?> offerCodeBanks;


    @JsonIgnore
    public Account getAccount() {

        return Account
                .builder()
                .id(id)
                .name(name)
                .build();
    }
}
