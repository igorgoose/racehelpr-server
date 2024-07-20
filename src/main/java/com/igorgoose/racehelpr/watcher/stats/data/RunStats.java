package com.igorgoose.racehelpr.watcher.stats.data;

import com.igorgoose.racehelpr.proxy.CatchModifications;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;

@CatchModifications
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(fluent = true, chain = true)
@Getter
@Setter
public class RunStats implements Copyable<RunStats> {
    String currentRun;
    RunProgressStats progress;
    HashMap<Integer, PilotRunStats> pilots;

    @Override
    public RunStats copy() {
        return new RunStats()
                .currentRun(currentRun)
                .progress(progress.copy())
                .pilots(new HashMap<>(pilots));
    }
}
