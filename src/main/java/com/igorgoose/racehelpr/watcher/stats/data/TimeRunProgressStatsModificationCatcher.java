package com.igorgoose.racehelpr.watcher.stats.data;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TimeRunProgressStatsModificationCatcher extends TimeRunProgressStats implements ModificationCatcher<TimeRunProgressStats> {
    private final TimeRunProgressStats actualStats;

    @Override
    public int timePassed() {
        return actualStats.timePassed();
    }

    @Override
    public int timeRemaining() {
        return actualStats.timeRemaining();
    }

    @Override
    public TimeRunProgressStats timePassed(int timePassed) {
        super.timePassed(timePassed);
        actualStats.timePassed(timePassed);
        return this;
    }

    @Override
    public TimeRunProgressStats timeRemaining(int timeRemaining) {
        super.timeRemaining(timeRemaining);
        actualStats.timeRemaining(timeRemaining);
        return this;
    }

    @Override
    public TimeRunProgressStats modificationSnapshot() {
        return copy();
    }
}
