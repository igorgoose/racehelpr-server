package com.igorgoose.racehelpr.watcher.stats.quali;

import com.igorgoose.racehelpr.watcher.stats.data.PilotRunStats;
import com.igorgoose.racehelpr.watcher.stats.data.PilotStats;
import com.igorgoose.racehelpr.watcher.stats.data.RaceStats;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Accessors(fluent = true, chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class QualiRaceStatsView {
    QualiLapView bestLap;
    QualiLapView currentLap;
    QualiQuickestKartView quickestKart;

    public QualiRaceStatsView(Integer pilotId, RaceStats raceStats) {
        if (raceStats.run() != null
                && raceStats.run().pilots() != null
                && raceStats.run().pilots().containsKey(pilotId)
        ) {
            PilotRunStats pilot = raceStats.run().pilots().get(pilotId);
            initBestLap(pilot);
            initCurrentLap(pilot);
        }
    }

    private void initBestLap(PilotRunStats pilot) {
        if (pilot.bestLap() == null) return;
        this.bestLap = new QualiLapView(pilot.bestLap());
    }

    private void initCurrentLap(PilotRunStats pilot) {
        if (pilot.currentLap() == null) return;
        this.currentLap = new QualiLapView(pilot.currentLap());
    }

    private void initQuickestKart(RaceStats raceStats) {
        // TODO add best lap to kart stats
    }
}
