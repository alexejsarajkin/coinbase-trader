package cz.coinbase.trader.data.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderBookItemDTO implements Comparable {

    private final BigDecimal price;
    private final BigDecimal size;
    private final String orderId;
    private final BigDecimal num;

    @JsonCreator
    public OrderBookItemDTO(List<String> limitOrders) {
        if (CollectionUtils.isEmpty(limitOrders) || limitOrders.size() < 3) {
            throw new IllegalArgumentException("LimitOrders was empty - check connection to the exchange");
        }

        price = new BigDecimal(limitOrders.get(0));
        size = new BigDecimal(limitOrders.get(1));

        if (isString(limitOrders.get(2))) {
            orderId = limitOrders.get(2);
        } else {
            orderId = null;
        }

        num = new BigDecimal(1);
    }

    @Override
    public int compareTo(Object o) {
        return this.getPrice().compareTo(((OrderBookItemDTO) o).getPrice()) * -1;
    }

    public boolean isString(String value) {
        boolean isDecimalSeparatorFound = false;

        for (char c : value.substring(1).toCharArray()) {
            if (!Character.isDigit(c)) {
                if (c == '.' && !isDecimalSeparatorFound) {
                    isDecimalSeparatorFound = true;
                    continue;
                }
                return false;
            }
        }
        return true;
    }
}
