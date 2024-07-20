package com.igorgoose.racehelpr.watcher.stats.data;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(fluent = true, chain = true)
@Data
public class PilotRunStats implements Copyable<PilotRunStats> {
    Integer pilotId;
    Integer kartId;
    LapStats bestLap;
    LapStats theoreticalLap;
    LapStats currentLap;
    AverageLapStats averageLap;


    @Override
    public PilotRunStats copy() {
        return new PilotRunStats()
                .pilotId(pilotId)
                .kartId(kartId)
                .bestLap(bestLap.copy())
                .theoreticalLap(theoreticalLap.copy())
                .currentLap(currentLap.copy())
                .averageLap(averageLap.copy());
    }
}
