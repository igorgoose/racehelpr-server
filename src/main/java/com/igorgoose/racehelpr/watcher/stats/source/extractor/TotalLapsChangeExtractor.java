package com.igorgoose.racehelpr.watcher.stats.source.extractor;

import com.igorgoose.racehelpr.domain.model.KartchronoMessage;
import com.igorgoose.racehelpr.watcher.stats.source.TotalLapsChange;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TotalLapsChangeExtractor implements SourceDatumChangeExtractor<TotalLapsChange> {

    @Override
    public Optional<TotalLapsChange> extractChange(KartchronoMessage message) {
        return Optional.ofNullable(message.getRunTotalLaps()).map(TotalLapsChange::new);
    }
}
