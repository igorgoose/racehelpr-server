package com.igorgoose.racehelpr.watcher.stats.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(fluent = true, chain = true)
@Data
public class KartLapStats implements Copyable<KartLapStats> {
    Integer kartId;
    LapStats lap;


    @Override
    public KartLapStats copy() {
        return new KartLapStats()
                .kartId(kartId())
                .lap(lap().copy());
    }
}
