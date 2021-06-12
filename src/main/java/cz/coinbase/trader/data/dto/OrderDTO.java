package cz.coinbase.trader.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.coinbase.trader.data.type.OrderSide;
import cz.coinbase.trader.data.type.OrderStatus;
import cz.coinbase.trader.data.type.ProductType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static cz.coinbase.trader.data.type.OrderSide.BUY;
import static cz.coinbase.trader.data.type.OrderSide.SELL;
import static cz.coinbase.trader.data.type.OrderStatus.DONE;
import static cz.coinbase.trader.data.type.OrderStatus.OPEN;

@Data
public class OrderDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("size")
    private BigDecimal size;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("product_id")
    private ProductType productId;

    @JsonProperty("side")
    private OrderSide side;

    @JsonProperty("stp")
    private String stp;

    @JsonProperty("type")
    private String type;

    @JsonProperty("time_in_force")
    private String timeInForce;

    @JsonProperty("post_only")
    private String postOnly;

    @JsonProperty("created_at")
    private OffsetDateTime createdAt;

    @JsonProperty("fill_fees")
    private String fillFees;

    @JsonProperty("filled_size")
    private String filledSize;

    @JsonProperty("executed_value")
    private String executedValue;

    @JsonProperty("status")
    private OrderStatus status;

    @Override
    public String toString() {
        return "Order -" +
                " side = " + side +
                ", price = " + price +
                ", size = " + size +
                ", status = " + status;
    }

    public boolean isSell() {
        return this.getSide().equals(SELL);
    }

    public boolean isBuy() {
        return this.getSide().equals(BUY);
    }

    public boolean isDone() {
        return this.getStatus() != null && this.getStatus().equals(DONE);
    }

    public boolean isOpen() {
        return this.getStatus() != null && this.getStatus().equals(OPEN);
    }

}
