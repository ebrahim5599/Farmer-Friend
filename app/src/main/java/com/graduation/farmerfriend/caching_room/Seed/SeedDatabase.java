package com.graduation.farmerfriend.caching_room.Seed;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = Seed.class,version = 2)
public abstract class SeedDatabase extends RoomDatabase {

    private static SeedDatabase instance;

    public abstract SeedDao seedDao();

    public static synchronized SeedDatabase getInstance(Context context) {

        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext(),
                     SeedDatabase.class, "SeedDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}