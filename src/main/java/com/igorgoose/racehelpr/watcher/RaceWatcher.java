package com.igorgoose.racehelpr.watcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.igorgoose.racehelpr.domain.entity.Race;
import com.igorgoose.racehelpr.domain.model.KartchronoMessage;
import com.igorgoose.racehelpr.watcher.stats.StatsUpdaterChain;
import com.igorgoose.racehelpr.watcher.stats.data.RaceStats;
import com.igorgoose.racehelpr.watcher.stats.source.SourceChangePackage;
import com.igorgoose.racehelpr.watcher.stats.source.extractor.SourceChangeExtractor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@RequiredArgsConstructor
@Slf4j
public class RaceWatcher {
    private final RaceStats raceStats = new RaceStats();
    private final AtomicBoolean initialized = new AtomicBoolean(false);
    private final ConcurrentHashMap<WebSocketSession, Boolean> sessions = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper;
    private final StatsUpdaterChain statsUpdaterChain;
    private final UpdateDispenser updateDispenser;
    private final Race race;
    private final SourceChangeExtractor sourceChangeExtractor;

    public void processMessage(String message) {
        try {
            KartchronoMessage kartchronoMessage = objectMapper.readValue(message, KartchronoMessage.class);
//            if (!initialized.compareAndSet(false, true)) {
//                statsUpdaterChain.initializeStats(kartchronoMessage, raceStats);
//            } else {
//                UpdatePackage updatePackage = statsUpdaterChain.updateStats(kartchronoMessage, raceStats);
//            }
//            updateDispenser.dispense(updatePackage, race.getMode()); // todo somehow interact with sessions
            SourceChangePackage sourceChangePackage = sourceChangeExtractor.extractChanges(kartchronoMessage);

        } catch (JsonProcessingException e) {
            log.warn("Failed to process message %s".formatted(message), e);
        }
    }

    public void attachClientSession(WebSocketSession session) {
        sessions.put(session, true);
    }

    public void detachClientSession(WebSocketSession session) {
        sessions.remove(session);
    }
}
