package com.igorgoose.racehelpr.watcher.stats.quali;

import com.igorgoose.racehelpr.watcher.stats.data.LapStats;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class QualiLapView {
    Integer lapTimeMs;
    ArrayList<Integer> sectorTimes;

    public QualiLapView(LapStats lapStats) {
        this.lapTimeMs = lapStats.sectorTimes().stream().mapToInt(Integer::intValue).sum();
        this.sectorTimes = new ArrayList<>(lapStats.sectorTimes()); // TODO consider concurrent modifications
    }
}
