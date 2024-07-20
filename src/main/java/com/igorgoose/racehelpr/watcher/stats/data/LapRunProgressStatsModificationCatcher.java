package com.igorgoose.racehelpr.watcher.stats.data;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LapRunProgressStatsModificationCatcher extends LapRunProgressStats implements ModificationCatcher<LapRunProgressStats> {
    private final LapRunProgressStats actualStats;

    @Override
    public int totalLaps() {
        return actualStats.totalLaps();
    }

    @Override
    public int lapsRemaining() {
        return actualStats.lapsRemaining();
    }

    @Override
    public LapRunProgressStats totalLaps(int totalLaps) {
        super.totalLaps(totalLaps);
        actualStats.totalLaps(totalLaps);
        return this;
    }

    @Override
    public LapRunProgressStats lapsRemaining(int lapsRemaining) {
        super.lapsRemaining(lapsRemaining);
        actualStats.lapsRemaining(lapsRemaining);
        return this;
    }

    @Override
    public LapRunProgressStats modificationSnapshot() {
        return copy();
    }
}
