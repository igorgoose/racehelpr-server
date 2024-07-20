package com.igorgoose.racehelpr.watcher.stats.data;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@RequiredArgsConstructor
public class LapStatsModificationCatcher extends LapStats implements ModificationCatcher<LapStats> {
    private final LapStats actualStats;

    @Override
    public ArrayList<Integer> sectorTimes() {
        return actualStats.sectorTimes();
    }

    @Override
    public int lapTime() {
        return actualStats.lapTime();
    }

    @Override
    public int currentSector() {
        return actualStats.currentSector();
    }

    @Override
    public LapStats sectorTimes(ArrayList<Integer> sectorTimes) {
        super.sectorTimes(sectorTimes);
        actualStats.sectorTimes(sectorTimes);
        return this;
    }

    @Override
    public LapStats modificationSnapshot() {
        return copy();
    }
}
