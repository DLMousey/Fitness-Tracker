package com.deadlyllama.trainingtracker.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(
        foreignKeys = {
                @ForeignKey(entity = Movement.class,
                            parentColumns = "id",
                            childColumns = "movementId"),
                @ForeignKey(entity = Session.class,
                            parentColumns = "id",
                            childColumns = "sessionId")
        },
        primaryKeys = { "movementId", "sessionId" },
        tableName = "session_movement_table"
)
public class SessionMovement {

    @NonNull
    public Long movementId;

    @NonNull
    public Long sessionId;

    public SessionMovement(Long movementId, Long sessionId) {
        this.movementId = movementId;
        this.sessionId = sessionId;
    }

    public void setMovementId(Long movementId) {
        this.movementId = movementId;
    }

    public Long getMovementId() {
        return this.movementId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getSessionId() {
        return this.sessionId;
    }
}
