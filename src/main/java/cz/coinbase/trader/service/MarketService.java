package cz.coinbase.trader.service;

import cz.coinbase.trader.data.dto.OrderBookDTO;
import cz.coinbase.trader.data.dto.TradeDTO;
import cz.coinbase.trader.data.type.ProductType;

import java.math.BigDecimal;
import java.util.List;

public interface MarketService {
    OrderBookDTO getOrderBook(ProductType productType, Integer level);

    List<TradeDTO> getTrades(String productId);

    List<BigDecimal> getOrderBookBid(ProductType productType, Integer level);


    List<BigDecimal> getOrderBookAsks(ProductType productType, Integer level);
}
