package com.igorgoose.racehelpr.watcher.stats;

import com.igorgoose.racehelpr.domain.model.KartchronoMessage;
import com.igorgoose.racehelpr.watcher.stats.data.RaceStats;

public interface StatsInitializer {
    void initialize(KartchronoMessage kartchronoMessage, RaceStats raceStats);
}
