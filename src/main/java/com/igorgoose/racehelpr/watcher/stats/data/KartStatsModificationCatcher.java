package com.igorgoose.racehelpr.watcher.stats.data;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KartStatsModificationCatcher extends KartStats implements ModificationCatcher<KartStats> {
    private final KartStats actualStats;

    @Override
    public String kartId() {
        return actualStats.kartId();
    }

    @Override
    public KartStats kartId(String kartId) {
        super.kartId(kartId);
        actualStats.kartId(kartId);
        return this;
    }

    @Override
    public KartStats modificationSnapshot() {
        return copy();
    }
}
