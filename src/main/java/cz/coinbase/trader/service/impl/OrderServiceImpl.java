package cz.coinbase.trader.service.impl;

import cz.coinbase.trader.data.dto.OrderDTO;
import cz.coinbase.trader.data.type.OrderSide;
import cz.coinbase.trader.data.type.OrderStatus;
import cz.coinbase.trader.service.ExchangeService;
import cz.coinbase.trader.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static cz.coinbase.trader.data.constants.Coinbase.ORDER_ENDPOINT;
import static cz.coinbase.trader.data.type.ProductType.BTC_EUR;
import static java.math.RoundingMode.HALF_UP;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final ExchangeService exchangeService;

    public OrderServiceImpl(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @Override
    public OrderDTO createOrder(OrderSide orderSide, BigDecimal amount, BigDecimal price) {
        OrderDTO order = new OrderDTO();
        order.setSize(amount);
        order.setPrice(price.setScale(2, HALF_UP));
        order.setSide(orderSide);
        order.setProductId(BTC_EUR);

        return exchangeService.post(
                ORDER_ENDPOINT,
                order,
                new ParameterizedTypeReference<>() {
                }
        );
    }

    @Override
    public OrderDTO getOrder(String orderId) {
        return exchangeService.get(
                ORDER_ENDPOINT + "/" + orderId,
                new ParameterizedTypeReference<>() {
                }
        );
    }

    @Override
    public List<OrderDTO> getOrders(OrderStatus orderStatus) {
        return exchangeService.getAsList(
                ORDER_ENDPOINT + "?status=" + orderStatus.getValue(),
                new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public OrderStatus getOrderStatus(String orderId) {
        OrderDTO order = getOrder(orderId);
        return (order != null) ? order.getStatus() : null;
    }

    @Override
    public String cancelOrder(String orderId) {
        return exchangeService.delete(
                ORDER_ENDPOINT + "/" + orderId,
                new ParameterizedTypeReference<>() {
                }
        );
    }
}
