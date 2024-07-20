package com.igorgoose.racehelpr.watcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.igorgoose.racehelpr.domain.entity.Race;
import com.igorgoose.racehelpr.watcher.stats.StatsUpdaterChain;
import com.igorgoose.racehelpr.watcher.stats.source.extractor.SourceChangeExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RaceWatcherFactory {
    private final ObjectMapper objectMapper;
    private final StatsUpdaterChain statsUpdaterChain;
    private final UpdateDispenser updateDispenser;
    private final SourceChangeExtractor sourceChangeExtractor;

    public RaceWatcher create(Race race) {
        return new RaceWatcher(objectMapper, statsUpdaterChain, updateDispenser, race, sourceChangeExtractor);
    }
}
