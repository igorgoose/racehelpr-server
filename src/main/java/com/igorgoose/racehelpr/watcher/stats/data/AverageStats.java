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
public class AverageStats implements Copyable<AverageStats> {
    Integer count;
    Integer sum;

    int value() {
        return count == null || sum == null ? -1 : count == 0 ? 0 : sum / count;
    }

    @Override
    public AverageStats copy() {
        return new AverageStats()
                .count(count)
                .sum(sum);
    }
}
