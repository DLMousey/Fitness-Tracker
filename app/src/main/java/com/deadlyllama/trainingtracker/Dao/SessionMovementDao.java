package com.deadlyllama.trainingtracker.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.deadlyllama.trainingtracker.Entity.Movement;
import com.deadlyllama.trainingtracker.Entity.Session;
import com.deadlyllama.trainingtracker.Entity.SessionMovement;

import java.util.List;

@Dao
public interface SessionMovementDao {

    @Insert
    void insert(SessionMovement sessionMovement);

    @Query("DELETE FROM session_movement_table")
    void deleteAll();

    @Delete
    void delete(SessionMovement sessionMovement);

    @Query("SELECT * FROM movement_table " +
           "INNER JOIN session_movement_table " +
           "ON movement_table.id=session_movement_table.movementId " +
           "WHERE session_movement_table.sessionId=:sessionId")
    LiveData<List<Movement>> getMovementsForSession(final int sessionId);

    @Query("SELECT * FROM session_table " +
           "INNER JOIN session_movement_table " +
           "ON session_table.id=session_movement_table.sessionId " +
           "WHERE session_movement_table.movementId=:movementId")
    LiveData<List<Session>> getSessionsForMovement(final int movementId);

    @Update
    void update(SessionMovement sessionMovement);
}
