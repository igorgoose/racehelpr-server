package com.igorgoose.racehelpr.persistence;

import com.igorgoose.racehelpr.domain.entity.Race;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RaceRepository {
    List<Race> findAll();
    Optional<Race> findById(UUID id);
    Race save(Race race);
    void delete(UUID id);
}
