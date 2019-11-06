package com.deadlyllama.trainingtracker;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "movement_table")
public class Movement {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private Long id;

    @ColumnInfo(name="name")
    private String name;

    @Nullable
    @ColumnInfo(name="description")
    private String description;

    @ColumnInfo(name="reps")
    private Integer reps;

    @ColumnInfo(name="sets")
    private Integer sets;

    @ColumnInfo(name="weight")
    private Double weight;

    @ForeignKey(entity = Session.class,
            parentColumns = "id",
            childColumns = "session_id",
            onDelete = ForeignKey.NO_ACTION)
    @ColumnInfo(name="session_id")
    private Long sessionId;

    public Movement(
            String name,
            String description,
            Integer reps,
            Integer sets,
            Double weight,
            Long sessionId
    ) {
        this.name = name;
        this.description = description;
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
        this.sessionId = sessionId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Integer getReps() {
        return this.reps;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getSets() {
        return this.sets;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getWeight() {
        return this.weight;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getSessionId() {
        return this.sessionId;
    }

}
