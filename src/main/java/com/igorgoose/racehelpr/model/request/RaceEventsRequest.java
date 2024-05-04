package com.igorgoose.racehelpr.model.request;

import com.igorgoose.racehelpr.model.RaceMode;

public record RaceEventsRequest(RaceMode mode, String trackId) {

}

