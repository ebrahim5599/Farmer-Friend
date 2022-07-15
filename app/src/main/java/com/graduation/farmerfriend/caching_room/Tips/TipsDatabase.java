package com.graduation.farmerfriend.caching_room.Tips;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.graduation.farmerfriend.Tips;

@Database(entities = Tips.class,version = 2)
public abstract class TipsDatabase extends RoomDatabase {

    private static TipsDatabase instance;

    public abstract TipsDao tipsDao();

    public static synchronized TipsDatabase getInstance(Context context) {

        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TipsDatabase.class, "TipsDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}