package cz.coinbase.trader.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.coinbase.trader.service.JsonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JsonServiceImpl implements JsonService {

    private final ObjectMapper objectMapper;

    public JsonServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Unable to serialize", e);
            throw new RuntimeException("Unable to serialize");
        }
    }
}
