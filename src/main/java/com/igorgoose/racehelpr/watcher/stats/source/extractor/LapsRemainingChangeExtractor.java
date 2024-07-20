package com.igorgoose.racehelpr.watcher.stats.source.extractor;

import com.igorgoose.racehelpr.domain.model.KartchronoMessage;
import com.igorgoose.racehelpr.watcher.stats.source.LapsRemainingChange;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LapsRemainingChangeExtractor implements SourceDatumChangeExtractor<LapsRemainingChange> {

    @Override
    public Optional<LapsRemainingChange> extractChange(KartchronoMessage message) {
        return Optional.ofNullable(message.getRunLapsRemaining()).map(LapsRemainingChange::new);
    }
}
