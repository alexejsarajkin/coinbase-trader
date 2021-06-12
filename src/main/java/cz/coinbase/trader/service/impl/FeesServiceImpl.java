package cz.coinbase.trader.service.impl;

import cz.coinbase.trader.data.dto.FeesDTO;
import cz.coinbase.trader.service.ExchangeService;
import cz.coinbase.trader.service.FeesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import static cz.coinbase.trader.data.constants.Coinbase.FEES_ENDPOINT;

@Service
@Slf4j
public class FeesServiceImpl implements FeesService {

    private final ExchangeService exchangeService;

    public FeesServiceImpl(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @Override
    public FeesDTO getFees() {
        return exchangeService.get(
                FEES_ENDPOINT,
                new ParameterizedTypeReference<>() {
                }
        );
    }
}
