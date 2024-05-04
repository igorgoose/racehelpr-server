package com.igorgoose.racehelpr.watcher;

import java.util.ArrayList;
import java.util.List;

class RaceStats {
    RunStats run;
    List<KartStats> karts = new ArrayList<>();
    List<DriverStats> drivers = new ArrayList<>();
}

class KartStats {
    String kartId;
}

class DriverStats {
    String id;
    String name;
    LapStats bestLap;
    LapStats theoreticalLap;
    LapStats currentLap;
    AverageLapStats averageLap;
}

class RunStats {
    String currentRun;
    int remainingRunTimeMs = -1;
    int totalRunTimeMs = -1;
}

class AverageLapStats {
    AverageStats sectorOne;
    AverageStats sectorTwo;
    AverageStats sectorThree;
}

class AverageStats {
    int count;
    int sum;

    int value() {
        return count == 0 ? 0 : sum / count;
    }
}

class LapStats {
    ArrayList<Integer> sectorTimes = new ArrayList<>(3);

    int lapTime() {
        return sectorTimes.stream().mapToInt(Integer::intValue).sum();
    }

    int currentSector() {
        return sectorTimes.size() + 1;
    }
}
