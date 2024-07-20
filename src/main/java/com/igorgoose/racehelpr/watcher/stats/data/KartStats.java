package com.igorgoose.racehelpr.watcher.stats.data;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Accessors(fluent = true, chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
public class KartStats implements Copyable<KartStats> {
    String kartId;

    @Override
    public KartStats copy() {
        return new KartStats().kartId(kartId);
    }
}
