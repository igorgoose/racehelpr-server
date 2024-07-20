package com.igorgoose.racehelpr.watcher.stats.data;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractModificationCatcher<T extends Copyable<T>> implements ModificationCatcher<T> {
    protected final T actualObject;
}
