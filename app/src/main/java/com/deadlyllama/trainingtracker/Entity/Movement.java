package com.deadlyllama.trainingtracker.Entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
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

    @ColumnInfo(name="muscle_group")
    private String muscleGroup;

    public Movement(
            String name,
            String description,
            String muscleGroup
    ) {
        this.name = name;
        this.description = description;
        this.muscleGroup = muscleGroup;
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

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public String getMuscleGroup() {
        return this.muscleGroup;
    }

}
