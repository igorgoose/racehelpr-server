package com.igorgoose.racehelpr.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.igorgoose.racehelpr.domain.model.request.RaceEventsRequest;
import com.igorgoose.racehelpr.persistence.RaceRepository;
import com.igorgoose.racehelpr.watcher.RaceWatcherManager;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@Component
public class RaceWebSocketHandler extends TextWebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(RaceWebSocketHandler.class);

    private final RaceRepository raceRepository;
    private final RaceWatcherManager raceWatcherManager;
    private final ObjectMapper objectMapper;
    private final Map<WebSocketSession, Boolean> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) {
        sessions.put(session, true);
        logger.debug("Added session {}", session);
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) {
        logger.debug("Session {} closed with status {}", session,status );
        sessions.remove(session);
        logger.debug("Removed session {}", session);
    }

    @Override
    public void handleTextMessage(@NonNull WebSocketSession session, TextMessage message) {
        String payload = message.getPayload();
        RaceEventsRequest request;
        try {
            request = objectMapper.readValue(payload, RaceEventsRequest.class);
        } catch (JsonProcessingException e) {
            logger.debug("Could not parse request {}, skipping it", payload);
            return;
        }
        raceRepository.findById(request.raceId()).ifPresentOrElse(
                race -> raceWatcherManager.attachSessionToRace(session, race),
                () -> logger.info("Could not attach to race watcher: invalid race id {}", request.raceId())
        );
    }
}
