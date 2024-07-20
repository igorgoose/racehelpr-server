package com.igorgoose.racehelpr.watcher.stats.source.extractor;

import com.igorgoose.racehelpr.domain.model.KartchronoMessage;
import com.igorgoose.racehelpr.watcher.stats.source.SourceDatumChange;

import java.util.Optional;

public interface SourceDatumChangeExtractor<T extends SourceDatumChange<?>> {
    Optional<T> extractChange(KartchronoMessage message);
}
