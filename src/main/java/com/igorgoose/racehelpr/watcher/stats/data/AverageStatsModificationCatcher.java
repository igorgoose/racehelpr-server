package com.igorgoose.racehelpr.watcher.stats.data;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AverageStatsModificationCatcher extends AverageStats implements ModificationCatcher<AverageStats> {
    private final AverageStats actualStats;

    @Override
    public Integer count() {
        return actualStats.count();
    }

    @Override
    public Integer sum() {
        return actualStats.sum();
    }

    @Override
    public AverageStats count(Integer count) {
        super.count(count);
        actualStats.count(count);
        return this;
    }

    @Override
    public AverageStats sum(Integer sum) {
        super.sum(sum);
        actualStats.sum(sum);
        return this;
    }

    @Override
    public AverageStats modificationSnapshot() {
        return new AverageStats()
                .count(this.count)
                .sum(this.sum);
    }
}
