package com.igorgoose.racehelpr.watcher.stats.source;

public interface SourceDatumChange<T> {
   <R extends T> R datum();
}
