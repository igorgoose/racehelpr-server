package com.igorgoose.racehelpr.watcher.stats.data;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KartLapStatsModificationCatcher extends KartLapStats implements ModificationCatcher<KartLapStats> {
    private final KartLapStats actualStats;

    @Override
    public Integer kartId() {
        return actualStats.kartId();
    }

    @Override
    public LapStats lap() {
        return actualStats.lap();
    }

    @Override
    public KartLapStats kartId(Integer kartId) {
        super.kartId(kartId);
        actualStats.kartId(kartId);
        return this;
    }

    @Override
    public KartLapStats lap(LapStats lap) {
        super.lap(lap);
        actualStats.lap(lap);
        return this;
    }

    @Override
    public KartLapStats modificationSnapshot() {
        return copy();
    }
}

