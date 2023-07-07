package ch.zli.gainthewifi.service.database;

import android.content.Context;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ch.zli.gainthewifi.modal.NetworkItem;

@Database(entities = {NetworkItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String  DATABASE_NAME = "rooms";

    private static AppDatabase instance;

    public abstract NetworkDao networkDao();

    public static AppDatabase getInstance(Context context) {
        if(instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}
