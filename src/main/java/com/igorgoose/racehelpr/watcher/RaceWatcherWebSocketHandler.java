package com.igorgoose.racehelpr.watcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;

import java.io.IOException;

class RaceWatcherWebSocketHandler implements WebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(RaceWatcherWebSocketHandler.class);
    private final RaceWatcher raceWatcher;

    RaceWatcherWebSocketHandler(RaceWatcher raceWatcher) {
        this.raceWatcher = raceWatcher;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        session.sendMessage(new TextMessage("""
                {"trackId": "68dddfb7cbe7ba1861db45bff7bdd308"}
                """));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
        switch (message) {
            case BinaryMessage _ -> logger.debug("Got a binary message, skipping it");
            case TextMessage textMessage -> raceWatcher.processMessage(textMessage.getPayload());
            default -> throw new IllegalStateException(STR."Unexpected value: \{message}");
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
