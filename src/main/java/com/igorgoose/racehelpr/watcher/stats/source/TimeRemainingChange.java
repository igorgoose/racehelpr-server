package com.igorgoose.racehelpr.watcher.stats.source;

public record TimeRemainingChange(Integer datum) implements SourceDatumChange<Integer> {
}
