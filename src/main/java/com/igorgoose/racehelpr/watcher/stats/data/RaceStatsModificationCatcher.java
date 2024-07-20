package com.igorgoose.racehelpr.watcher.stats.data;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RaceStatsModificationCatcher extends RaceStats implements ModificationCatcher<RaceStats> {
    private final RaceStats actualStats;

    public RaceStatsModificationCatcher(RaceStats actualStats) {
        this.actualStats = actualStats;
    }

    @Override
    public String raceName() {
        return actualStats.raceName();
    }

    @Override
    public RaceStats raceName(String raceName) {
        super.raceName(raceName);
        actualStats.raceName(raceName);
        return this;
    }

    @Override
    public RaceStats run(RunStats run) {
        var wrappedRun = new RunStatsModificationCatcher(run);
        super.run(wrappedRun);
        actualStats.run(run);
        return this;
    }

    @Override
    public RaceStats karts(Set<KartStats> karts) {
        Set<KartStats> wrappedKarts = karts.stream()
                .map(KartStatsModificationCatcher::new)
                .collect(Collectors.toSet());
        super.karts(wrappedKarts);
        actualStats.karts(karts);
        return this;
    }

    @Override
    public RaceStats pilots(Map<Integer, PilotStats> pilots) {
        Map<Integer, PilotStats> wrappedPilots = pilots.values().stream()
                .map(PilotStatsModificationCatcher::new)
                .collect(Collectors.toMap(PilotStats::id, Function.identity()));
        super.pilots(wrappedPilots);
        actualStats.pilots(pilots);
        return this;
    }

    @Override
    public RaceStats modificationSnapshot() {
        return copy();
    }
}
