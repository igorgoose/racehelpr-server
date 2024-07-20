package com.igorgoose.racehelpr.watcher.stats.source;

import java.util.Map;
import java.util.Optional;

public record SourceChangePackage(Map<Class<? extends SourceDatumChange>, SourceDatumChange> changes) {

    @SuppressWarnings("unchecked")
    public <T extends SourceDatumChange<?>> Optional<T> findChange(Class<T> clazz) {
        return Optional.ofNullable(changes.get(clazz)).map(it -> (T) it);
    }

    @SuppressWarnings("unchecked")
    public <D, T extends SourceDatumChange<D>> Optional<D> findChangeDatum(Class<T> clazz) {
        return Optional.ofNullable(changes.get(clazz)).map(it -> (T) it).map(SourceDatumChange::datum);
    }
}

