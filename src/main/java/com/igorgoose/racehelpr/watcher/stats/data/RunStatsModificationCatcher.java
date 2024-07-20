package com.igorgoose.racehelpr.watcher.stats.data;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;

@RequiredArgsConstructor
public class RunStatsModificationCatcher extends RunStats implements ModificationCatcher<RunStats> {
    private final RunStats actualStats;

    @Override
    public String currentRun() {
        return actualStats.currentRun();
    }

    @Override
    public RunProgressStats progress() {
        return actualStats.progress();
    }

    @Override
    public HashMap<Integer, PilotRunStats> pilots() {
        return actualStats.pilots();
    }

    @Override
    public RunStats currentRun(String currentRun) {
        super.currentRun(currentRun);
        actualStats.currentRun(currentRun);
        return this;
    }

    @Override
    public RunStats progress(RunProgressStats progress) {
        super.progress(progress);
        actualStats.progress(progress);
        return this;
    }

    @Override
    public RunStats pilots(HashMap<Integer, PilotRunStats> pilots) {
        super.pilots(pilots);
        actualStats.pilots(pilots);
        return this;
    }

    @Override
    public RunStats modificationSnapshot() {
        return copy();
    }
}
