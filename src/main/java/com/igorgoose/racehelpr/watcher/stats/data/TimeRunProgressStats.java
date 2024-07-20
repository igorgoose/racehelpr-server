package com.igorgoose.racehelpr.watcher.stats.data;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Accessors(fluent = true, chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class TimeRunProgressStats implements RunProgressStats {
    int totalTime;
    int timeRemaining;

    @Override
    public TimeRunProgressStats copy() {
        return new TimeRunProgressStats()
                .totalTime(totalTime)
                .timeRemaining(timeRemaining);
    }
}
