package cz.coinbase.trader.data.type;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
    OPEN("open"),
    PENDING("pending"),
    ACTIVE("active"),
    DONE("done");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
