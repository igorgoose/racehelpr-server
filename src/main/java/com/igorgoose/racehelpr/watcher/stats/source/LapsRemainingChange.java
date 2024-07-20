package com.igorgoose.racehelpr.watcher.stats.source;

public record LapsRemainingChange(Integer datum) implements SourceDatumChange<Integer> {
}
