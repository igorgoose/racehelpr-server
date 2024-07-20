package com.igorgoose.racehelpr.watcher;

import com.igorgoose.racehelpr.domain.model.RaceMode;

public interface UpdateDispenser {
    void dispense(UpdatePackage updatePackage, RaceMode raceMode);
}

