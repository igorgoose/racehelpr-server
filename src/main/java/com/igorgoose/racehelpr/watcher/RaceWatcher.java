package com.igorgoose.racehelpr.watcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;

public class RaceWatcher {
    private RaceStats raceStats;
    private final ObjectMapper objectMapper;

    public RaceWatcher(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void processMessage(String message) {
        try {
            LinkedHashMap<String, Object> messageMap = objectMapper.readValue(message, new TypeReference<>() {
            });

        } catch (JsonProcessingException e) {

        }
    }

}
