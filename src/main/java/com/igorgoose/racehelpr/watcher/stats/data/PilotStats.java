package com.igorgoose.racehelpr.watcher.stats.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(fluent = true, chain = true)
@Data
public class PilotStats implements Copyable<PilotStats> {
    int id;
    int teamId;
    String name;
    KartLapStats bestLap;
    KartLapStats bestTheoreticalLap;
    AverageLapStats averageLap;

    @Override
    public PilotStats copy() {
        return new PilotStats()
                .id(id)
                .teamId(teamId)
                .name(name)
                .bestLap(bestLap.copy())
                .bestTheoreticalLap(bestTheoreticalLap.copy())
                .averageLap(averageLap.copy());
    }
}
