package cz.coinbase.trader.data.type;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ProductType {
    BTC_EUR("BTC-EUR");

    private final String value;

    ProductType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public static ProductType fromString(String text) {
        for (ProductType productType : ProductType.values()) {
            if (productType.value.equalsIgnoreCase(text)) {
                return productType;
            }
        }
        return null;
    }
}
