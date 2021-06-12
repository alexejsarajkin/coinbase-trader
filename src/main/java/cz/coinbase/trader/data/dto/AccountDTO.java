package cz.coinbase.trader.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("balance")
    private BigDecimal balance;

    @JsonProperty("available")
    private String available;

    @JsonProperty("hold")
    private String hold;

    @JsonProperty("profile_id")
    private String profileId;

    @Override
    public String toString() {
        return "Account - " +
                "currency = " + currency +
                ", balance = " + balance;
    }
}
