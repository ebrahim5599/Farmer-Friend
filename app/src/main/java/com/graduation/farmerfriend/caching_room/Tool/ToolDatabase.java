package com.graduation.farmerfriend.caching_room.Tool;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = Tool.class,version = 2)
public abstract class ToolDatabase extends RoomDatabase {

    private static ToolDatabase instance;

    public abstract ToolDao toolDao();

    public static synchronized ToolDatabase getInstance(Context context) {

        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ToolDatabase.class, "ToolDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}