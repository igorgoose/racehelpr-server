package com.igorgoose.racehelpr.domain.entity;

import com.igorgoose.racehelpr.domain.model.RaceMode;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(fluent = true, chain = true)
@Data
public class Race {
    UUID id;
    String name;
    String trackId;
    RaceMode mode;
    Instant startTime;
    Instant endTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Race race)) return false;
        return Objects.equals(id, race.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
