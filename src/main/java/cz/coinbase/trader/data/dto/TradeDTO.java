package cz.coinbase.trader.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class TradeDTO {

    @JsonProperty("time")
    private Instant time;

    @JsonProperty("trade_id")
    private Long tradeId;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("size")
    private BigDecimal size;

    @JsonProperty("side")
    private String side;
}
