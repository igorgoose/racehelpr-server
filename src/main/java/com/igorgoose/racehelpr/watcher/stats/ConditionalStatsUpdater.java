package com.igorgoose.racehelpr.watcher.stats;

import com.igorgoose.racehelpr.domain.model.KartchronoMessage;
import com.igorgoose.racehelpr.watcher.stats.data.RaceStats;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ConditionalStatsUpdater implements StatsUpdater {

    @Override
    public void applyUpdate(KartchronoMessage kartchronoMessage, RaceStats stats) {
        log.debug("Checking if update needed [updater={}]", this.getClass().getSimpleName());
        if (!shouldUpdate(kartchronoMessage, stats)) {
            log.debug("Update not needed [updater={}]", this.getClass().getSimpleName());
            return;
        }
        log.debug("Applying update [updater={}]", this.getClass().getSimpleName());
        doApplyUpdate(kartchronoMessage, stats);
    }

    protected abstract void doApplyUpdate(KartchronoMessage kartchronoMessage, RaceStats raceStats);

    protected boolean shouldUpdate(KartchronoMessage kartchronoMessage, RaceStats raceStats) { return false; }
}
