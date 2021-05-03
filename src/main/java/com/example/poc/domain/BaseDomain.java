package com.example.poc.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Abstract BaseDomain class inherited by all our domain models
 * This class has 2 fields with default values needed by all other models
 * This class uses Lombok annotations
 */
@Getter
@SuperBuilder
@NoArgsConstructor
public abstract class BaseDomain {

    @Builder.Default
    protected LocalDateTime created = LocalDateTime.now();
    @Builder.Default
    protected LocalDateTime updated = LocalDateTime.now();

}
