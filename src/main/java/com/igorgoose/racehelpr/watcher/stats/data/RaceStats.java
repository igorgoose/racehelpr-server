package com.igorgoose.racehelpr.watcher.stats.data;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(fluent = true, chain = true)
@Getter
@Setter
public class RaceStats implements Copyable<RaceStats> {
    String raceName;
    RunStats run;
    Set<KartStats> karts;
    Map<Integer, PilotStats> pilots;


    @Override
    public RaceStats copy() {
        return new RaceStats()
                .raceName(raceName)
                .run(run.copy())
                .karts(new LinkedHashSet<>(karts))
                .pilots(new LinkedHashMap<>(pilots));
    }
}

