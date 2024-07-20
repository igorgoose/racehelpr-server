package com.igorgoose.racehelpr.watcher.stats;

import com.igorgoose.racehelpr.domain.model.KartchronoMessage;
import com.igorgoose.racehelpr.watcher.UpdatePackage;
import com.igorgoose.racehelpr.watcher.stats.data.RaceStats;
import com.igorgoose.racehelpr.watcher.stats.data.RaceStatsModificationCatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StatsUpdaterChainImpl implements StatsUpdaterChain {
    private final List<StatsUpdater> updaters;
    private final List<StatsInitializer> initializers;

    /**
     * Called when the stats have not been initialized, e.g. upon first kartchrono message
     * @param kartchronoMessage kartchrono message with initial data
     * @param raceStats uninitialized race stats object
     */
    @Override
    public RaceStats initializeStats(KartchronoMessage kartchronoMessage, RaceStats raceStats) {
        for (StatsInitializer initializer : initializers) {
            initializer.initialize(kartchronoMessage, raceStats);
        }
        return raceStats;
    }

    public RaceStats updateStats(KartchronoMessage kartchronoMessage, RaceStats stats) {
        // TODO initialize modification catcher and use it below
        var modificationCatcher = new RaceStatsModificationCatcher(stats);
        for (StatsUpdater updater : updaters) {
            updater.applyUpdate(kartchronoMessage, stats);
        }
        return new UpdatePackage(); //TODO actual update package
    }
}
