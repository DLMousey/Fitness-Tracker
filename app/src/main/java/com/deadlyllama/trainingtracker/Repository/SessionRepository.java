package com.deadlyllama.trainingtracker.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.deadlyllama.trainingtracker.Dao.SessionDao;
import com.deadlyllama.trainingtracker.Entity.Session;
import com.deadlyllama.trainingtracker.WorkoutsDatabase;

import java.util.List;

public class SessionRepository {

    private SessionDao sessionDao;
    private LiveData<List<Session>> allSessions;

    public SessionRepository(Application application) {
        WorkoutsDatabase db = WorkoutsDatabase.getDatabase(application);
        sessionDao = db.sessionDao();
        allSessions = sessionDao.getAllSessions();
    }

    public LiveData<List<Session>> getAllSessions() {
        return allSessions;
    }

    public void insert(Session session) {
        new insertAsyncTask(sessionDao).execute(session);
    }

    public void delete(Session session) {
        new deleteAsyncTask(sessionDao).execute(session);
    }

    public void update(Session session) {
        new updateAsyncTask(sessionDao).execute(session);
    }

    private static class insertAsyncTask extends AsyncTask<Session, Void, Void> {
        private SessionDao asyncSessionDao;

        insertAsyncTask(SessionDao dao) {
            asyncSessionDao = dao;
        }

        @Override
        protected Void doInBackground(final Session... params) {
            asyncSessionDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Session, Void, Void> {
        private SessionDao asyncSessionDao;

        deleteAsyncTask(SessionDao dao) {
            asyncSessionDao = dao;
        }

        @Override
        protected Void doInBackground(final Session... params) {
            asyncSessionDao.delete(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Session, Void, Void> {
        private SessionDao asyncSessionDao;

        updateAsyncTask(SessionDao dao) {
            asyncSessionDao = dao;
        }

        @Override
        protected Void doInBackground(final Session... params) {
            asyncSessionDao.update(params[0]);
            return null;
        }
    }
}
