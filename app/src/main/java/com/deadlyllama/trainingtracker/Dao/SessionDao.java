package com.deadlyllama.trainingtracker.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.deadlyllama.trainingtracker.Entity.Session;

import java.util.List;

@Dao
public interface SessionDao {

    @Insert
    Long insert(Session session);

    @Query("DELETE FROM session_table")
    void deleteAll();

    @Delete
    void delete(Session session);

    @Query("SELECT * FROM session_table ORDER BY createdAt DESC")
    LiveData<List<Session>> getAllSessions();

    @Query("SELECT * FROM session_table ORDER BY createdAt DESC")
    List<Session> getAllSessionsSync();

    @Update
    void update(Session session);
}
