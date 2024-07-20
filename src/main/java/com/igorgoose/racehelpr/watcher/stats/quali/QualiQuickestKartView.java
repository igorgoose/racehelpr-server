package com.igorgoose.racehelpr.watcher.stats.quali;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Accessors(fluent = true, chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class QualiQuickestKartView {
    String kart;
    String location;
    QualiLapView lap;
}
