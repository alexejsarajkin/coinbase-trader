package cz.coinbase.trader.service.impl;

import cz.coinbase.trader.data.dto.OrderBookDTO;
import cz.coinbase.trader.data.dto.OrderBookItemDTO;
import cz.coinbase.trader.data.dto.TradeDTO;
import cz.coinbase.trader.data.type.ProductType;
import cz.coinbase.trader.service.ExchangeService;
import cz.coinbase.trader.service.MarketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static cz.coinbase.trader.data.constants.Coinbase.*;

@Service
@Slf4j
public class MarketServiceImpl implements MarketService {

    private final ExchangeService exchangeService;

    public MarketServiceImpl(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @Override
    public OrderBookDTO getOrderBook(ProductType productType, Integer level) {

        String marketDataEndpoint = PRODUCTS_ENDPOINT + "/" + productType.getValue() + BOOK_PATH;

        if (level != 1) {
            marketDataEndpoint += "?level=" + level;
        }

        // TODO: Add sorting

        return exchangeService.get(
                marketDataEndpoint,
                new ParameterizedTypeReference<>() {
                }
        );
    }

    @Override
    public List<BigDecimal> getOrderBookBid(ProductType productType, Integer level) {
        return getOrderBook(productType, level)
                .getBids()
                .stream()
                .map(OrderBookItemDTO::getPrice)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<BigDecimal> getOrderBookAsks(ProductType productType, Integer level) {
        return getOrderBook(productType, level)
                .getAsks()
                .stream()
                .map(OrderBookItemDTO::getPrice)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<TradeDTO> getTrades(String productId) {
        String tradesEndpoint = PRODUCTS_ENDPOINT + "/" + productId + TRADES_PATH;

        return exchangeService.getAsList(
                tradesEndpoint,
                new ParameterizedTypeReference<>() {
                }
        );
    }
}
