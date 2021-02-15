package com.example.oblig;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {ImgItem.class}, version = 2)

public abstract class ImgDatabase extends RoomDatabase {
    private static ImgDatabase db;

    public static ImgDatabase getInstance(Context context) {
        if(db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(), ImgDatabase.class, "imgDb").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return db;
    }
    public abstract ImgDEO imgdeo();
}
