package com.deadlyllama.trainingtracker;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {
        Session.class,
        Movement.class
}, version = 4, exportSchema = false)
public abstract class WorkoutsDatabase extends RoomDatabase {

    public abstract SessionDao sessionDao();
    public abstract MovementDao movementDao();

    private static WorkoutsDatabase INSTANCE;

    public static WorkoutsDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WorkoutsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WorkoutsDatabase.class, "workouts_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }

//    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback(){
//        @Override
//        public void onOpen(@NonNull SupportSQLiteDatabase db) {
//            super.onOpen(db);
//            new PopulateDbAsync(INSTANCE).execute();
//        }
//    };
//
//    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
//
//        private final SessionDao dao;
//
//    }
}
