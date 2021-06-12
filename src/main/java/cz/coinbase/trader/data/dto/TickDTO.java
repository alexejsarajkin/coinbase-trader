package cz.coinbase.trader.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TickDTO {

    @JsonProperty("trade_id")
    private String tradeId;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("size")
    private String size;

    @JsonProperty("bid")
    private String bid;

    @JsonProperty("ask")
    private String ask;

    @JsonProperty("volume")
    private String volume;

    @JsonProperty("time")
    private String time;
}
