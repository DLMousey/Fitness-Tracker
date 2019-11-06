package com.deadlyllama.trainingtracker;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SessionViewModel extends AndroidViewModel {

    private SessionRepository repository;
    private LiveData<List<Session>> allSessions;

    public SessionViewModel(Application application) {
        super(application);
        repository = new SessionRepository(application);
        allSessions = repository.getAllSessions();
    }

    LiveData<List<Session>> getAllSessions() {
        return allSessions;
    }

    public void insert(Session session) {
        repository.insert(session);
    }

    public void delete(Session session) {
        repository.delete(session);
    }
}
