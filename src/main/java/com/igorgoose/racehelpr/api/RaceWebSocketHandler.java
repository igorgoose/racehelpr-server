package com.igorgoose.racehelpr.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.igorgoose.racehelpr.model.request.RaceEventsRequest;
import com.igorgoose.racehelpr.session.RaceSessionConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RaceWebSocketHandler extends TextWebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(RaceWebSocketHandler.class);

    private final ObjectMapper objectMapper;
    private final Map<WebSocketSession, Boolean> sessions = new ConcurrentHashMap<>();

    public RaceWebSocketHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception {
        sessions.put(session, true);
        logger.debug(STR."Added session \{session}");
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) throws Exception {
        logger.debug(STR."Session \{session} closed with status \{status}");
        sessions.remove(session, true);
        logger.debug(STR."Removed session \{session}");
    }

    @Override
    public void handleTextMessage(@NonNull WebSocketSession session, TextMessage message) {
        String payload = message.getPayload();
        RaceEventsRequest request;
        try {
            request = objectMapper.readValue(payload, RaceEventsRequest.class);
        } catch (JsonProcessingException e) {
            logger.debug(STR."Could not parse request \{payload}, skipping it");
            return;
        }
        session.getAttributes().put("config", new RaceSessionConfig(request.mode(), request.trackId()));
    }
}
