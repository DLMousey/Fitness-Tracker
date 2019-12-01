package com.deadlyllama.trainingtracker.Entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(tableName = "session_table")
public class Session implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private Long id;

    @ColumnInfo(name="location")
    private String location;

    @ColumnInfo(name="createdAt")
    private String createdAt;

    @Nullable
    @ColumnInfo(name="updatedAt")
    private String updatedAt;

//    @Relation(parentColumn = "id", entityColumn="session_id",
//    entity = Movement.class)
//    public List<Movement> movements;

    public Session() {
        this.createdAt = new Date().toString();
    }

    public Session(String location) {
        this.location = location;
        this.createdAt = new Date().toString();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

//    public void setAllMovements(List<Movement> movements) {
//        this.movements = movements;
//    }
//
//    public List<Movement> getMovements() {
//        return this.movements;
//    }
}
