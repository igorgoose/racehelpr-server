package com.igorgoose.racehelpr.watcher.stats.data;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PROTECTED)
@Accessors(fluent = true, chain = true)
@Getter
@Setter
public class AverageLapStats implements Copyable<AverageLapStats> {
    AverageStats sectorOne;
    AverageStats sectorTwo;
    AverageStats sectorThree;

    @Override
    public AverageLapStats copy() {
        return new AverageLapStats()
                .sectorOne(sectorOne.copy())
                .sectorTwo(sectorTwo.copy())
                .sectorThree(sectorThree.copy());
    }
}
