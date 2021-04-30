package com.example.poc.multiversion.v2.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor
public abstract class BaseDomain {

    @Builder.Default
    protected LocalDateTime created = LocalDateTime.now();
    @Builder.Default
    protected LocalDateTime updated = LocalDateTime.now();

}
