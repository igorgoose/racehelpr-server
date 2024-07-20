package com.igorgoose.racehelpr.watcher.stats.modifier;

public sealed interface ModifierInput permits ModifierInput.NonApplicable, ModifierInput.Input {
    final class NonApplicable implements ModifierInput {
    }

    record Input<T>(T data) implements ModifierInput {
    }
}
