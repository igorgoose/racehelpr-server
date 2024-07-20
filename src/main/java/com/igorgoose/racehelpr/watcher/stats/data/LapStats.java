package com.igorgoose.racehelpr.watcher.stats.data;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(fluent = true, chain = true)
@Getter
@Setter
public class LapStats implements Copyable<LapStats> {
    ArrayList<Integer> sectorTimes = new ArrayList<>(3);

    int lapTime() {
        return sectorTimes.stream().mapToInt(Integer::intValue).sum();
    }

    int currentSector() {
        return sectorTimes.size() + 1;
    }


    @Override
    public LapStats copy() {
        return new LapStats().sectorTimes(new ArrayList<>(sectorTimes));
    }
}
