package com.igorgoose.racehelpr.watcher.stats.source.extractor;

import com.igorgoose.racehelpr.domain.model.KartchronoMessage;
import com.igorgoose.racehelpr.watcher.stats.source.SourceChangePackage;
import com.igorgoose.racehelpr.watcher.stats.source.SourceDatumChange;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SourceChangeExtractor {
    private final List<SourceDatumChangeExtractor<?>> extractors;

    @SuppressWarnings("unchecked")
    public SourceChangePackage extractChanges(KartchronoMessage kartchronoMessage) {
         Map<Class<? extends SourceDatumChange>, SourceDatumChange> changes = extractors.stream()
                .map(extractor -> extractor.extractChange(kartchronoMessage))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(change -> (SourceDatumChange<Object>) change)
                .collect(Collectors.toMap(
                        SourceDatumChange<Object>::getClass,
                        Function.identity()
                ));
        return new SourceChangePackage(changes);
    }
}
