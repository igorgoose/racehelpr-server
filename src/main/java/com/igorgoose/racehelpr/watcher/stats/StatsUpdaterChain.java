package com.igorgoose.racehelpr.watcher.stats;

import com.igorgoose.racehelpr.domain.model.KartchronoMessage;
import com.igorgoose.racehelpr.watcher.stats.data.RaceStats;
import com.igorgoose.racehelpr.watcher.UpdatePackage;

public interface StatsUpdaterChain {
    RaceStats initializeStats(KartchronoMessage kartchronoMessage, RaceStats raceStats);
    RaceStats updateStats(KartchronoMessage kartchronoMessage, RaceStats stats);
}

