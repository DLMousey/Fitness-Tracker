package com.deadlyllama.trainingtracker;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.deadlyllama.trainingtracker.Dao.MovementDao;
import com.deadlyllama.trainingtracker.Dao.SessionDao;
import com.deadlyllama.trainingtracker.Dao.SessionMovementDao;
import com.deadlyllama.trainingtracker.Entity.Movement;
import com.deadlyllama.trainingtracker.Entity.Session;
import com.deadlyllama.trainingtracker.Entity.SessionMovement;

@Database(entities = {
        Session.class,
        Movement.class,
        SessionMovement.class
}, version = 8, exportSchema = false)
public abstract class WorkoutsDatabase extends RoomDatabase {

    public abstract SessionDao sessionDao();
    public abstract MovementDao movementDao();
    public abstract SessionMovementDao sessionMovementDao();

    private static WorkoutsDatabase INSTANCE;

    public static WorkoutsDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WorkoutsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WorkoutsDatabase.class, "workouts_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomDatabaseCallback)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final MovementDao movementDao;

        PopulateDbAsync(WorkoutsDatabase db) {
            movementDao = db.movementDao();
        }

        @Override
        protected Void doInBackground(final Void...params) {
            movementDao.deleteAll();

            movementDao.insert(new Movement("Dumbell Shoulder Press", "Arnold Style", "shoulders"));
            movementDao.insert(new Movement("Lateral Raise", "The lightest lift you'll ever do", "shoulders"));
            movementDao.insert(new Movement("Bicep Curl", "Cheat on your girls, not on your curls", "arms"));
            movementDao.insert(new Movement("Hammer Curl", "FOREARM PUMPS", "arms"));
            movementDao.insert(new Movement("Bench Press", "BURN IT OUUUUUUUUUUUUUT", "chest"));
            movementDao.insert(new Movement("Incline Dumbbell Press", "Because you're a freak of nature, you're STRONGER on incline", "chest"));
            movementDao.insert(new Movement("Lat Pull", "You didn't see me hiding those back there eh?", "back"));
            movementDao.insert(new Movement("Deadlift", "Did i hit that lift?", "back"));
            movementDao.insert(new Movement("Crunches", "Do them till you poop", "core"));
            movementDao.insert(new Movement("Plank", "Difficult, but not impressive", "core"));
            movementDao.insert(new Movement("Leg Extension", "OH GOD IT HURTS PLEASE MAKE IT STOP", "legs"));
            movementDao.insert(new Movement("Leg Curl", "OH GOD IT HURTS EVEN MORE GOING THE OTHER WAY", "legs"));

            return null;
        }
    }
}