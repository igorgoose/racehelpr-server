package com.igorgoose.racehelpr.watcher;

import com.igorgoose.racehelpr.domain.entity.Race;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
@Service
public class RaceWatcherManager {
    private final ConcurrentHashMap<Race, RaceWatcher> raceWatchers = new ConcurrentHashMap<>();

    public void attachSessionToRace(WebSocketSession session, Race race) {
        RaceWatcher raceWatcher = raceWatchers.get(race);
        if (raceWatcher == null) {
            log.warn("No active race watcher for race {}, not attaching session", race);
            return;
        }

        raceWatcher.attachClientSession(session);
    }


}
