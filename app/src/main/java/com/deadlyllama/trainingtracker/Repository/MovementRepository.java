package com.deadlyllama.trainingtracker.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.deadlyllama.trainingtracker.Dao.MovementDao;
import com.deadlyllama.trainingtracker.Entity.Movement;
import com.deadlyllama.trainingtracker.WorkoutsDatabase;

import java.util.List;

public class MovementRepository {

    private MovementDao movementDao;
    private LiveData<List<Movement>> allMovements;

    public MovementRepository(Application application) {
        WorkoutsDatabase db = WorkoutsDatabase.getDatabase(application);
        movementDao = db.movementDao();
        allMovements = movementDao.getAllMovements();
    }

    public LiveData<List<Movement>> getAllMovements() {
        return allMovements;
    }

    public void insert(Movement movement) {
        new insertAsyncTask(movementDao).execute(movement);
    }

    public void delete(Movement movement) {
        new deleteAsyncTask(movementDao).execute(movement);
    }

    public void update(Movement movement) {
        new updateAsyncTask(movementDao).execute(movement);
    }

    private static class insertAsyncTask extends AsyncTask<Movement, Void, Void> {
        private MovementDao asyncMovementDao;

        insertAsyncTask(MovementDao dao) {
            asyncMovementDao = dao;
        }

        @Override
        protected Void doInBackground(final Movement... params) {
            asyncMovementDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Movement, Void, Void> {
        private MovementDao asyncMovementDao;

        deleteAsyncTask(MovementDao dao) {
            asyncMovementDao = dao;
        }

        @Override
        protected Void doInBackground(final Movement... params) {
            asyncMovementDao.delete(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Movement, Void, Void> {
        private MovementDao asyncMovementDao;

        updateAsyncTask(MovementDao dao) {
            asyncMovementDao = dao;
        }

        @Override
        protected Void doInBackground(final Movement... params) {
            asyncMovementDao.update(params[0]);
            return null;
        }
    }
}
