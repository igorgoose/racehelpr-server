package com.igorgoose.racehelpr.watcher.stats.modifier;

import com.igorgoose.racehelpr.watcher.stats.data.RaceStats;
import com.igorgoose.racehelpr.watcher.stats.source.SourceChangePackage;

public interface StatsModifier<T> {
    void modify(RaceStats stats, ModifierInput.Input<T> input);
    ModifierInput prepareInput(SourceChangePackage changePackage);
}
