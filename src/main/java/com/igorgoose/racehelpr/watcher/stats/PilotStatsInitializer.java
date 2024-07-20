package com.igorgoose.racehelpr.watcher.stats;

import com.igorgoose.racehelpr.domain.model.KartchronoMessage;
import com.igorgoose.racehelpr.watcher.stats.data.PilotStats;
import com.igorgoose.racehelpr.watcher.stats.data.RaceStats;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PilotStatsInitializer implements StatsInitializer {

    private boolean hasPilotsData(KartchronoMessage kartchronoMessage) {
        return kartchronoMessage.getPilots() != null;
    }

    @Override
    public void initialize(KartchronoMessage kartchronoMessage, RaceStats raceStats) {
        if (!hasPilotsData(kartchronoMessage)) {
            log.debug("No pilots data found for race stats initialization");
            return;
        }

        var pilots = kartchronoMessage.getPilots().entrySet().stream()
                .map(entry ->
                        new PilotStats()
                                .setId(entry.getKey())
                                .setName(entry.getValue().getName())
                                .setTeamId(entry.getValue().getTeamId())
                )
                .collect(Collectors.toMap(PilotStats::getId, Function.identity()));
        raceStats.setPilots(pilots);
    }
}
