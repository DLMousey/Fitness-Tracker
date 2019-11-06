package com.deadlyllama.trainingtracker;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SessionDao {

    @Insert
    void insert(Session session);

    @Query("DELETE FROM session_table")
    void deleteAll();

    @Delete
    void delete(Session session);

    @Query("SELECT * FROM session_table ORDER BY createdAt DESC")
    LiveData<List<Session>> getAllSessions();

    @Update
    void update(Session session);
}
