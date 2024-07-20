package com.igorgoose.racehelpr.session;

import com.igorgoose.racehelpr.domain.model.RaceMode;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class RaceSessionConfig {
    private static final VarHandle REQUIRES_SNAPSHOT_HANDLE;

    static {
        try {
            REQUIRES_SNAPSHOT_HANDLE = MethodHandles.lookup().findVarHandle(RaceSessionConfig.class, "requiresSnapshot", boolean.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private final RaceMode raceMode;
    private final String trackId;
    private boolean requiresSnapshot;

    public RaceSessionConfig(RaceMode raceMode, String trackId) {
        this(raceMode, trackId, true);
    }

    public RaceSessionConfig(RaceMode raceMode, String trackId, boolean requiresSnapshot) {
        this.raceMode = raceMode;
        this.trackId = trackId;
    }

    public void setRequiresSnapshot(boolean requiresSnapshot) {
        REQUIRES_SNAPSHOT_HANDLE.setOpaque(this, requiresSnapshot);
    }

    public boolean getRequiresSnapshot() {
        return (boolean) REQUIRES_SNAPSHOT_HANDLE.getOpaque(this);
    }
}
