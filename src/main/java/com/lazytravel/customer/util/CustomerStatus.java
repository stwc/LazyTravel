package com.lazytravel.customer.util;

public enum CustomerStatus {
    BANNED("0"), ACTIVE("1"), NOT_AUTH("2");

    private final String value;

    CustomerStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
