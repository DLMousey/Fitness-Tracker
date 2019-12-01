package com.deadlyllama.trainingtracker.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.deadlyllama.trainingtracker.Entity.Movement;

import java.util.List;

@Dao
public interface MovementDao {

    @Insert
    void insert(Movement movement);

    @Query("DELETE FROM movement_table")
    void deleteAll();

    @Delete
    void delete(Movement movement);

    @Query("SELECT * FROM movement_table")
    LiveData<List<Movement>> getAllMovements();

    @Query("SELECT * FROM movement_table WHERE muscle_group = :muscleGroup")
    LiveData<List<Movement>> getMovementsByMuscleGroup(String muscleGroup);

    @Update
    void update(Movement movement);
}
