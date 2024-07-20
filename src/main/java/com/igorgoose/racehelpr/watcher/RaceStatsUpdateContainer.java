package com.igorgoose.racehelpr.watcher;

import com.igorgoose.racehelpr.watcher.stats.data.KartStats;
import com.igorgoose.racehelpr.watcher.stats.data.PilotStats;
import com.igorgoose.racehelpr.watcher.stats.data.RaceStats;
import com.igorgoose.racehelpr.watcher.stats.data.RunStats;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
public class RaceStatsUpdateContainer extends RaceStats {
    private final RaceStats actualStats;

    @Override
    public RaceStats setRaceName(String raceName) {
        actualStats.setRaceName(raceName);
        return super.setRaceName(raceName);
    }

    @Override
    public RaceStats setRun(RunStats run) {
        return super.setRun(run);
    }

    @Override
    public RaceStats setKarts(Set<KartStats> karts) {
        return super.setKarts(karts);
    }

    @Override
    public RaceStats setPilots(Map<Integer, PilotStats> pilots) {
        return super.setPilots(pilots);
    }
}
