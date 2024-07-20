package com.igorgoose.racehelpr.watcher.stats.source.extractor;

import com.igorgoose.racehelpr.domain.model.KartchronoMessage;
import com.igorgoose.racehelpr.watcher.stats.source.TotalTimeChange;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TotalTimeChangeExtractor implements SourceDatumChangeExtractor<TotalTimeChange> {

    @Override
    public Optional<TotalTimeChange> extractChange(KartchronoMessage message) {
        Optional<Integer> timeRemaining = Optional.ofNullable(message.getRunTimeRemaining());
        Optional<Integer> timePassed = Optional.ofNullable(message.getRunTimePassed());

        if (timeRemaining.isPresent() && timePassed.isPresent())
            return Optional.of(new TotalTimeChange(timeRemaining.get() + timePassed.get()));

        return Optional.empty();
    }
}
