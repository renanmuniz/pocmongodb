package com.pocmongodb.util;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
    PAID_OUT("PAID_OUT", "PAID"),
    PAYMENT_PENDING("PAYMENT_PENDING", "PENDING"),
    CANCELED("ORDER_CANCELED", "CANCElED");

    private final String key;
    private final String value;

    Status(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
