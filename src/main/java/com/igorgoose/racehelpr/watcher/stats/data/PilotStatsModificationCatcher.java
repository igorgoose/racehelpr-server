package com.igorgoose.racehelpr.watcher.stats.data;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PilotStatsModificationCatcher extends PilotStats implements ModificationCatcher<PilotStats> {
    private final PilotStats actualStats;

    @Override
    public int id() {
        return actualStats.id();
    }

    @Override
    public int teamId() {
        return actualStats.teamId();
    }

    @Override
    public String name() {
        return actualStats.name();
    }

    @Override
    public KartLapStats bestLap() {
        return actualStats.bestLap();
    }

    @Override
    public KartLapStats bestTheoreticalLap() {
        return actualStats.bestTheoreticalLap();
    }

    @Override
    public AverageLapStats averageLap() {
        return actualStats.averageLap();
    }

    @Override
    public PilotStats id(int id) {
        super.id(id);
        actualStats.id(id);
        return this;
    }

    @Override
    public PilotStats teamId(int teamId) {
        super.teamId(teamId);
        actualStats.teamId(teamId);
        return this;
    }

    @Override
    public PilotStats name(String name) {
        super.name(name);
        actualStats.name(name);
        return this;
    }

    @Override
    public PilotStats bestLap(KartLapStats bestLap) {
        super.bestLap(bestLap);
        actualStats.bestLap(bestLap);
        return this;
    }

    @Override
    public PilotStats bestTheoreticalLap(KartLapStats bestTheoreticalLap) {
        super.bestTheoreticalLap(bestTheoreticalLap);
        actualStats.bestTheoreticalLap(bestTheoreticalLap);
        return this;
    }

    @Override
    public PilotStats averageLap(AverageLapStats averageLap) {
        super.averageLap(averageLap);
        actualStats.averageLap(averageLap);
        return this;
    }

    @Override
    public PilotStats modificationSnapshot() {
        return copy();
    }
}
