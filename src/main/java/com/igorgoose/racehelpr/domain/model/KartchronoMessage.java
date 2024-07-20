package com.igorgoose.racehelpr.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class KartchronoMessage {
    @JsonProperty("100")
    Integer runTimePassed;
    @JsonProperty("101")
    Integer runTimeRemaining;
    @JsonProperty("102")
    Integer runTotalLaps;
    @JsonProperty("103")
    Integer runLapsRemaining;
    @JsonProperty("104")
    String runName;
    @JsonProperty("105")
    String raceName;
    @JsonProperty("106")
    Integer flagStatus;
    @JsonProperty("108")
    String finishType;
    Map<Integer, Pilot> pilots;

    @Getter
    @Setter
    public static class Pilot {
        @JsonProperty("4")
        String name;
        @JsonProperty("31")
        Integer teamId;
    }
}
