package com.igorgoose.racehelpr.watcher;

import java.util.LinkedHashMap;

interface StatsUpdater {
    void updateStats(LinkedHashMap<String, Object> messageMap, RaceStats stats);
}

