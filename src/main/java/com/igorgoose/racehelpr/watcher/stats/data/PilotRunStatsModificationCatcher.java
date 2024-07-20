package com.igorgoose.racehelpr.watcher.stats.data;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PilotRunStatsModificationCatcher extends PilotRunStats implements ModificationCatcher<PilotRunStats> {
    private final PilotRunStats actualStats;

    @Override
    public Integer pilotId() {
        return actualStats.pilotId();
    }

    @Override
    public Integer kartId() {
        return actualStats.kartId();
    }

    @Override
    public LapStats bestLap() {
        return actualStats.bestLap();
    }

    @Override
    public LapStats theoreticalLap() {
        return actualStats.theoreticalLap();
    }

    @Override
    public LapStats currentLap() {
        return actualStats.currentLap();
    }

    @Override
    public AverageLapStats averageLap() {
        return actualStats.averageLap();
    }

    @Override
    public PilotRunStats pilotId(Integer pilotId) {
        super.pilotId(pilotId);
        actualStats.pilotId(pilotId);
        return this;
    }

    @Override
    public PilotRunStats kartId(Integer kartId) {
        super.kartId(kartId);
        actualStats.kartId(kartId);
        return this;
    }

    @Override
    public PilotRunStats bestLap(LapStats bestLap) {
        super.bestLap(bestLap);
        actualStats.bestLap(bestLap);
        return this;
    }

    @Override
    public PilotRunStats theoreticalLap(LapStats theoreticalLap) {
        super.theoreticalLap(theoreticalLap);
        actualStats.theoreticalLap(theoreticalLap);
        return this;
    }

    @Override
    public PilotRunStats currentLap(LapStats currentLap) {
        super.currentLap(currentLap);
        actualStats.currentLap(currentLap);
        return this;
    }

    @Override
    public PilotRunStats averageLap(AverageLapStats averageLap) {
        super.averageLap(averageLap);
        actualStats.averageLap(averageLap);
        return this;
    }

    @Override
    public PilotRunStats modificationSnapshot() {
        return copy();
    }
}
