package cz.coinbase.trader.service.impl;

import cz.coinbase.trader.data.constants.ExchangeConstants;
import cz.coinbase.trader.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.management.RuntimeErrorException;
import java.security.InvalidKeyException;
import java.time.Instant;
import java.util.Base64;

@Service
@Slf4j
public class SecurityServiceImpl implements SecurityService {

    @Value("${coinbase.secret-key}")
    private String secretKey;

    @Value("${coinbase.base-url}")
    private String baseUrl;

    @Value("${coinbase.public-key}")
    private String publicKey;

    @Value("${coinbase.passphrase}")
    private String passphrase;

    @Override
    public HttpEntity<String> securityHeaders(String endpoint, String method, String jsonBody) {
        String timestamp = Instant.now().getEpochSecond() + "";
        String resource = endpoint.replace(baseUrl, "");

        return new RequestBuilder()
                .headerAccept(MediaType.APPLICATION_JSON)
                .headerContentType(MediaType.APPLICATION_JSON)
                .headerAccessKey(publicKey)
                .headerAccessSign(generate(resource, method, jsonBody, timestamp))
                .headerAccessTimestamp(timestamp)
                .headerAccessPassphrase(passphrase)
                .build(jsonBody);
    }

    private String generate(String requestPath, String method, String body, String timestamp) {
        try {
            String prehash = timestamp + method.toUpperCase() + requestPath + body;
            byte[] secretDecoded = Base64.getDecoder().decode(secretKey);
            SecretKeySpec keyspec = new SecretKeySpec(secretDecoded, ExchangeConstants.SHARED_MAC.getAlgorithm());
            Mac sha256 = (Mac) ExchangeConstants.SHARED_MAC.clone();
            sha256.init(keyspec);
            return Base64.getEncoder().encodeToString(sha256.doFinal(prehash.getBytes()));
        } catch (CloneNotSupportedException | InvalidKeyException e) {
            e.printStackTrace();
            throw new RuntimeErrorException(new Error("Cannot set up authentication headers."));
        }
    }
}
