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
public class LapRunProgressStats implements RunProgressStats {
    int totalLaps;
    int lapsRemaining;

    @Override
    public LapRunProgressStats copy() {
        return new LapRunProgressStats()
                .lapsRemaining(this.lapsRemaining)
                .totalLaps(this.totalLaps);
    }
}
