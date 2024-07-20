package com.igorgoose.racehelpr.watcher.stats;

import com.igorgoose.racehelpr.domain.model.KartchronoMessage;
import com.igorgoose.racehelpr.watcher.stats.data.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RunStatsInitializer implements StatsInitializer {

    @Override
    public void initialize(KartchronoMessage kartchronoMessage, RaceStats raceStats) {
        if (kartchronoMessage.getRunName() == null) {
            log.debug("No run initialization data, skipping initialization");
            return;
        }

        var run = new RunStats()
                .currentRun(kartchronoMessage.getRunName());
        setProgressStats(kartchronoMessage, run);
        raceStats.run(run);
    }

    private void setProgressStats(KartchronoMessage kartchronoMessage, RunStats run) {
        RunProgressStats progress = switch (kartchronoMessage.getFinishType()) {
            case "finishByTime" -> new TimeRunProgressStats()
                    .timePassed(kartchronoMessage.getRunTimePassed())
                    .timeRemaining(kartchronoMessage.getRunTimeRemaining());
            case "finishByLaps" -> new LapRunProgressStats() // TODO clarify the name
                    .totalLaps(kartchronoMessage.getRunTotalLaps())
                    .lapsRemaining(kartchronoMessage.getRunLapsRemaining());
            default -> {
                log.warn("Unexpected finish type: {}", kartchronoMessage.getFinishType());
                yield null;
            }
        };
        run.progress(progress);
    }
}
