package com.graduation.farmerfriend.caching_room.Tool;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.graduation.farmerfriend.ecommerce_models.Product;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface ToolDao {

    @Insert
    Completable insertTool(ArrayList<Tool> Tool);

    @Query("Select * from Tool")
    Single<List<Tool>> getTool();
}
