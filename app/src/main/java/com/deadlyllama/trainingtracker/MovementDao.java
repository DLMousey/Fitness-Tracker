package com.deadlyllama.trainingtracker;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MovementDao {

    @Insert
    void insert(Movement movement);

    @Query("DELETE FROM movement_table")
    void deleteAll();

    @Delete
    void delete(Movement movement);

    @Query("SELECT * FROM movement_table ORDER BY reps DESC")
    LiveData<List<Movement>> getAllMovements();

    @Update
    void update(Movement movement);
}
