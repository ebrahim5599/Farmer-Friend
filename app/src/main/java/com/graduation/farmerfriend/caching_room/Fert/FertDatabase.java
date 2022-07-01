package com.graduation.farmerfriend.caching_room.Fert;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Fert.class,version = 2)
public abstract class FertDatabase extends RoomDatabase {

    private static FertDatabase instance;

    public abstract FertDao fertDao();

    public static synchronized FertDatabase getInstance(Context context) {

        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FertDatabase.class, "FertDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}

