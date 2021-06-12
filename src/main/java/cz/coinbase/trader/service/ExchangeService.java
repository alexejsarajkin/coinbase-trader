package cz.coinbase.trader.service;

import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public interface ExchangeService {

    <T> T get(String endpoint, ParameterizedTypeReference<T> type);

    <T> List<T> getAsList(String endpoint, ParameterizedTypeReference<T[]> type);

    <T, R> T post(String endpoint, R jsonObject, ParameterizedTypeReference<T> type);

    <T> T delete(String endpoint, ParameterizedTypeReference<T> type);
}
