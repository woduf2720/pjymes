package com.example.pjymes.domain;

import lombok.Getter;

@Getter
public enum OrderStatus {
    INITIAL(0),
    PARTIAL(1),
    FULLY(2);

    private final int value;

    OrderStatus(int value) {
        this.value = value;
    }
}
