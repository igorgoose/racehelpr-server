package com.igorgoose.racehelpr.watcher.stats.source.extractor;

import com.igorgoose.racehelpr.domain.model.KartchronoMessage;
import com.igorgoose.racehelpr.watcher.stats.source.RunProgressTypeChange;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RunProgressTypeChangeExtractor implements SourceDatumChangeExtractor<RunProgressTypeChange> {

    @Override
    public Optional<RunProgressTypeChange> extractChange(KartchronoMessage message) {
        return Optional.ofNullable(message.getFinishType()).map(RunProgressTypeChange::new);
    }
}
