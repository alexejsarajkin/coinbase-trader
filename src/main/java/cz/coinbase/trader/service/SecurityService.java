package cz.coinbase.trader.service;

import org.springframework.http.HttpEntity;

public interface SecurityService {
    HttpEntity<String> securityHeaders(String endpoint, String method, String jsonBody);
}
