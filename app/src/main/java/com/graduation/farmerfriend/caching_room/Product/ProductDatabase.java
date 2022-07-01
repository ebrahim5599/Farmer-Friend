package com.graduation.farmerfriend.caching_room.Product;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.graduation.farmerfriend.ecommerce_models.Product;

@Database(entities = Product.class,version = 2)

public abstract class ProductDatabase extends RoomDatabase {

    private static ProductDatabase instance;

    public abstract ProductDao productDao();

    public static synchronized ProductDatabase getInstance(Context context) {

        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext(),
                     ProductDatabase.class, "ProductDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
