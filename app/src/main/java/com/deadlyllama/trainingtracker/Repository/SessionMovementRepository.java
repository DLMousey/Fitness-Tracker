package com.deadlyllama.trainingtracker.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.deadlyllama.trainingtracker.Dao.SessionMovementDao;
import com.deadlyllama.trainingtracker.Entity.Movement;
import com.deadlyllama.trainingtracker.Entity.SessionMovement;
import com.deadlyllama.trainingtracker.WorkoutsDatabase;

import java.util.List;

public class SessionMovementRepository {

    private SessionMovementDao sessionMovementDao;

    public SessionMovementRepository(Application application) {
        WorkoutsDatabase db = WorkoutsDatabase.getDatabase(application);
        sessionMovementDao = db.sessionMovementDao();
    }

    public void insert(SessionMovement sessionMovement) {
        new insertAsyncTask(sessionMovementDao).execute(sessionMovement);
    }

    public void delete(SessionMovement sessionMovement) {
        new deleteAsyncTask(sessionMovementDao).execute(sessionMovement);
    }

    public void update(SessionMovement sessionMovement) {
        new updateAsyncTask(sessionMovementDao).execute(sessionMovement);
    }

    private static class insertAsyncTask extends AsyncTask<SessionMovement, Void, Void> {
        private SessionMovementDao asyncSessionMovementDao;

        insertAsyncTask(SessionMovementDao dao) {
            asyncSessionMovementDao = dao;
        }

        @Override
        protected Void doInBackground(final SessionMovement... params) {
            asyncSessionMovementDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<SessionMovement, Void, Void> {
        private SessionMovementDao asyncSessionMovementDao;

        deleteAsyncTask(SessionMovementDao dao) {
            asyncSessionMovementDao = dao;
        }

        @Override
        protected Void doInBackground(final SessionMovement... params) {
            asyncSessionMovementDao.delete(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<SessionMovement, Void, Void> {
        private SessionMovementDao asyncSessionMovementDao;

        updateAsyncTask(SessionMovementDao dao) {
            asyncSessionMovementDao = dao;
        }

        @Override
        protected Void doInBackground(final SessionMovement... params) {
            asyncSessionMovementDao.update(params[0]);
            return null;
        }
    }
}
