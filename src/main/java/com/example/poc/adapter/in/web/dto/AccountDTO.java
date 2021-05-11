package com.example.poc.adapter.in.web.dto;

import com.example.poc.domain.Asset;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


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
}
