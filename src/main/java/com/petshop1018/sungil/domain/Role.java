package com.petshop1018.sungil.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("USER"),
    ADMIN("ADMIN"),
    SOCIAL("SOCIAL");
    private final String value;
}
