package com.igorgoose.racehelpr.watcher.stats;

import com.igorgoose.racehelpr.domain.model.KartchronoMessage;
import com.igorgoose.racehelpr.watcher.stats.data.RaceStats;

public interface StatsUpdater {
    void applyUpdate(KartchronoMessage kartchronoMessage, RaceStats stats);
}


