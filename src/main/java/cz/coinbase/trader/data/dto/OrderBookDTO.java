package cz.coinbase.trader.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OrderBookDTO {

    @JsonProperty("sequence")
    private Long sequence;

    @JsonProperty("bids") // Buy
    private List<OrderBookItemDTO> bids; // price, size, orders

    @JsonProperty("asks") // Sell
    private List<OrderBookItemDTO> asks; // price, size, orders
}
