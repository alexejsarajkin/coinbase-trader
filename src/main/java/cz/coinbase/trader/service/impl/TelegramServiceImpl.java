package cz.coinbase.trader.service.impl;

import cz.coinbase.trader.service.TelegramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@Slf4j
public class TelegramServiceImpl implements TelegramService {

    private final RestTemplate restTemplate;

    @Value("${telegram.key}")
    private String apiKey;

    @Value("${telegram.chat-id}")
    private String chatId;

    public TelegramServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendMessage(String message) {
        URI uri = UriComponentsBuilder
                .fromUriString("https://api.telegram.org")
                .path("/bot" + apiKey)
                .path("/sendMessage")
                .queryParam("chat_id", chatId)
                .queryParam("text", message)
                .build()
                .toUri();

        try {
            restTemplate.getForEntity(
                    uri,
                    String.class
            );
        } catch (Exception e) {
            log.error("Send telegram message failed", e);
        }
    }
}
