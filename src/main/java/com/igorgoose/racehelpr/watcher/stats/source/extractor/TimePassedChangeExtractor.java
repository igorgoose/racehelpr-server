package com.igorgoose.racehelpr.watcher.stats.source.extractor;

import com.igorgoose.racehelpr.domain.model.KartchronoMessage;
import com.igorgoose.racehelpr.watcher.stats.source.TimeRemainingChange;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TimePassedChangeExtractor implements SourceDatumChangeExtractor<TimeRemainingChange>{

    @Override
    public Optional<TimeRemainingChange> extractChange(KartchronoMessage message) {
        return Optional.ofNullable(message.getRunTimePassed()).map(TimeRemainingChange::new);
    }
}
