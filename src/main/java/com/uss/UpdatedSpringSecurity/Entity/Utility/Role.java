package com.uss.UpdatedSpringSecurity.Entity.Utility;

import lombok.Data;
import lombok.Getter;

@Getter
public enum Role {
    A_USER("A_USER"),
    G_USER("G_USER");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

