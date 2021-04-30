package com.example.poc.multiversion.v1;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor
public class BaseEntity {
    @Builder.Default
    protected LocalDateTime created = LocalDateTime.now();
    @Builder.Default
    protected LocalDateTime updated = LocalDateTime.now();
}
