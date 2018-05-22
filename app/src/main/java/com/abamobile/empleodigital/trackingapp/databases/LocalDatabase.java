package com.abamobile.empleodigital.trackingapp.databases;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Singleton database that uses the persistence library Room
 * @version 1.0
 * @see RoomDatabase
 */
@Database(entities = {ShiftEntity.class, TaskEntity.class}, version = 1)
public abstract class LocalDatabase extends RoomDatabase {

    private static LocalDatabase INSTANCE = null;

    public abstract ShiftDAO getShiftDAO();
    public abstract TaskDAO getTaskDAO();

    /**
     * Singleton. Returns or creates the instance.
     * @param context
     * @return instance of the class
     */
    public static LocalDatabase getINSTANCE(Context context) {
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), LocalDatabase.class, "LocalDatabase").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
