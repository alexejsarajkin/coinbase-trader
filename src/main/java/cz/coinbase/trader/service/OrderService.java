package cz.coinbase.trader.service;

import cz.coinbase.trader.data.dto.OrderDTO;
import cz.coinbase.trader.data.type.OrderSide;
import cz.coinbase.trader.data.type.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderSide orderSide, BigDecimal amount, BigDecimal price);

    OrderDTO getOrder(String orderId);

    List<OrderDTO> getOrders(OrderStatus orderStatus);

    OrderStatus getOrderStatus(String orderId);

    String cancelOrder(String orderId);
}
