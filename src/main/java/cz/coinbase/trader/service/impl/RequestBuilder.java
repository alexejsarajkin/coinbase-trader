package cz.coinbase.trader.service.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;

public class RequestBuilder {

    private static final String CB_ACCESS_KEY = "CB-ACCESS-KEY";
    private static final String CB_ACCESS_SIGN = "CB-ACCESS-SIGN";
    private static final String CB_ACCESS_TIMESTAMP = "CB-ACCESS-TIMESTAMP";
    private static final String CB_ACCESS_PASSPHRASE = "CB-ACCESS-PASSPHRASE";

    private final HttpHeaders headers = new HttpHeaders();

    public RequestBuilder headerAccept(MediaType value) {
        this.headers.setAccept(List.of(value));
        return this;
    }

    public RequestBuilder headerContentType(MediaType value) {
        this.headers.setContentType(value);
        return this;
    }

    public RequestBuilder headerAccessKey(String value) {
        this.headers.add(CB_ACCESS_KEY, value);
        return this;
    }

    public RequestBuilder headerAccessSign(String value) {
        this.headers.add(CB_ACCESS_SIGN, value);
        return this;
    }

    public RequestBuilder headerAccessTimestamp(String value) {
        this.headers.add(CB_ACCESS_TIMESTAMP, value);
        return this;
    }

    public RequestBuilder headerAccessPassphrase(String value) {
        this.headers.add(CB_ACCESS_PASSPHRASE, value);
        return this;
    }

    public <T> HttpEntity<T> build(T body) {
        return new HttpEntity<>(body, headers);
    }
}
