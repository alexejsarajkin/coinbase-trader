package cz.coinbase.trader.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FeesDTO {

    @JsonProperty("maker_fee_rate")
    private String makerFeeRate;

    @JsonProperty("taker_fee_rate")
    private String takerFeeRate;

    @JsonProperty("usd_volume")
    private String usdVolume;

    @Override
    public String toString() {
        return "FeesDTO - " +
                "makerFeeRate = " + makerFeeRate +
                ", takerFeeRate = " + takerFeeRate +
                ", usdVolume = " + usdVolume;
    }
}
