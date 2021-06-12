package cz.coinbase.trader.service.impl;

import cz.coinbase.trader.data.constants.Coinbase;
import cz.coinbase.trader.data.dto.TickDTO;
import cz.coinbase.trader.data.type.ProductType;
import cz.coinbase.trader.service.ExchangeService;
import cz.coinbase.trader.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static cz.coinbase.trader.data.constants.Coinbase.TICKER_PATH;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ExchangeService exchangeService;

    public ProductServiceImpl(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @Override
    public BigDecimal getProductPrice(ProductType productType) {
        TickDTO tick = getTick(productType);

        return tick != null ? tick.getPrice() : null;
    }

    private TickDTO getTick(ProductType productType) {
        return exchangeService.get(
                Coinbase.PRODUCTS_ENDPOINT + "/" + productType.getValue() + TICKER_PATH,
                new ParameterizedTypeReference<>() {
                });
    }
}
