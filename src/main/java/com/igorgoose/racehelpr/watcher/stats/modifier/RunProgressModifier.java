package com.igorgoose.racehelpr.watcher.stats.modifier;

import com.igorgoose.racehelpr.watcher.stats.data.LapRunProgressStats;
import com.igorgoose.racehelpr.watcher.stats.data.RaceStats;
import com.igorgoose.racehelpr.watcher.stats.data.RunProgressStats;
import com.igorgoose.racehelpr.watcher.stats.data.TimeRunProgressStats;
import com.igorgoose.racehelpr.watcher.stats.source.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class RunProgressModifier implements StatsModifier<RunProgressModifier.Input> {

    @Override
    public void modify(RaceStats stats, ModifierInput.Input<Input> input) {
        var data = input.data();
        if (data.type.isPresent()) {
            if (data.total.isPresent() && data.remaining.isPresent()) {
                stats.run().progress(buildRunProgressStats(data.type.get(), data.total.get(), data.remaining.get()));
                return;
            }
            else log.warn("Run progress change missing total or remaining data {}", data);
        }


    }

    private RunProgressStats buildRunProgressStats(String type, int total, int remaining) {
        return switch (type) {
            case "finishByTime" -> new TimeRunProgressStats().totalTime(total).timeRemaining(remaining);
            case "finishByLaps" -> new LapRunProgressStats().totalLaps(total).lapsRemaining(remaining);
            default -> {
                log.warn("Unknown run progress stats type: {}", type);
                yield null;
            }
        };
    }

    @Override
    public ModifierInput prepareInput(SourceChangePackage changePackage) {
        var type = changePackage.findChangeDatum(RunProgressTypeChange.class);
        var total = changePackage.findChangeDatum(TotalLapsChange.class);
        var remaining = changePackage.findChangeDatum(LapsRemainingChange.class);

        if (total.isEmpty()) total = changePackage.findChangeDatum(TotalTimeChange.class);
        if (remaining.isEmpty()) remaining = changePackage.findChangeDatum(TimeRemainingChange.class);

        return new ModifierInput.Input<>(new Input(type, total, remaining));
    }

    public record Input(Optional<String> type, Optional<Integer> total, Optional<Integer> remaining) {
    }
}
