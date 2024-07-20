package com.igorgoose.racehelpr.watcher.stats.data;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AverageLapStatsModificationCatcher extends AverageLapStats implements ModificationCatcher<AverageLapStats> {
    private final AverageLapStats actualStats;

    @Override
    public AverageStats sectorOne() {
        return actualStats.sectorOne();
    }

    @Override
    public AverageStats sectorTwo() {
        return actualStats.sectorTwo();
    }

    @Override
    public AverageStats sectorThree() {
        return actualStats.sectorThree();
    }

    @Override
    public AverageLapStats sectorOne(AverageStats sectorOne) {
        super.sectorOne(sectorOne);
        actualStats.sectorOne(sectorOne);
        return this;
    }

    @Override
    public AverageLapStats sectorTwo(AverageStats sectorTwo) {
        super.sectorTwo(sectorOne);
        actualStats.sectorTwo(sectorOne);
        return this;
    }

    @Override
    public AverageLapStats sectorThree(AverageStats sectorThree) {
        super.sectorThree(sectorOne);
        actualStats.sectorThree(sectorOne);
        return this;
    }

    @Override
    public AverageLapStats modificationSnapshot() {
        return copy();
    }
}
