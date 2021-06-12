package cz.coinbase.trader.service.impl;

import cz.coinbase.trader.service.ExchangeService;
import cz.coinbase.trader.service.JsonService;
import cz.coinbase.trader.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.springframework.http.HttpMethod.DELETE;

@Service
@Slf4j
public class ExchangeServiceImpl implements ExchangeService {

    @Value("${coinbase.base-url}")
    private String baseUrl;

    @Value("${coinbase.public-key}")
    private String publicKey;

    @Value("${coinbase.passphrase}")
    private String passphrase;

    private final RestTemplate restTemplate;

    private final JsonService jsonService;

    private final SecurityService securityService;

    public ExchangeServiceImpl(RestTemplate restTemplate, JsonService jsonService, SecurityService securityService) {
        this.restTemplate = restTemplate;
        this.jsonService = jsonService;
        this.securityService = securityService;
    }

    @Override
    public <T> T get(String resourcePath, ParameterizedTypeReference<T> responseType) {
        try {
            ResponseEntity<T> responseEntity = restTemplate.exchange(
                    baseUrl + resourcePath,
                    HttpMethod.GET,
                    securityService.securityHeaders(
                            resourcePath,
                            "GET",
                            ""),
                    responseType);
            return responseEntity.getBody();
        } catch (Exception ex) {
            log.error("GET request Failed for " + resourcePath, ex);
        }
        return null;
    }

    @Override
    public <T> List<T> getAsList(String resourcePath, ParameterizedTypeReference<T[]> responseType) {
        T[] result = get(resourcePath, responseType);

        return result == null ? emptyList() : Arrays.asList(result);
    }

    @Override
    public <T, R> T post(String resourcePath, R jsonObj, ParameterizedTypeReference<T> responseType) {
        String jsonBody = jsonService.toJson(jsonObj);

        try {
            ResponseEntity<T> response = restTemplate.exchange(
                    baseUrl + resourcePath,
                    HttpMethod.POST,
                    securityService.securityHeaders(resourcePath, "POST", jsonBody),
                    responseType);
            return response.getBody();
        } catch (Exception ex) {
            log.error("POST request Failed for" + resourcePath, ex);
        }
        return null;
    }

    @Override
    public <T> T delete(String resourcePath, ParameterizedTypeReference<T> responseType) {
        try {
            ResponseEntity<T> response = restTemplate.exchange(
                    baseUrl + resourcePath,
                    DELETE,
                    securityService.securityHeaders(resourcePath, "DELETE", ""),
                    responseType);
            return response.getBody();
        } catch (Exception ex) {
            log.error("DELETE request Failed for '" + resourcePath, ex);
        }
        return null;
    }

}
