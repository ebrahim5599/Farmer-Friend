package com.graduation.farmerfriend.caching_room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.graduation.farmerfriend.forecast_models.RootForeCast;

@Database(entities = RootForeCast.class ,version = 2)
@TypeConverters(Converters.class)

public abstract class WeatherDatabase extends RoomDatabase {

    private static WeatherDatabase instance;

    public abstract WeatherDao weatherDao();

    public static synchronized WeatherDatabase getInstance(Context context) {
        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext(),
                    WeatherDatabase.class, "WeatherDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}

