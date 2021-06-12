package cz.coinbase.trader.data.type;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Currency {
    EUR("EUR"),
    BTC("BTC");

    private final String value;

    Currency(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
