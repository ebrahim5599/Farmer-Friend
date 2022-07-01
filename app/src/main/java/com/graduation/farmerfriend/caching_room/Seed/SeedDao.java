package com.graduation.farmerfriend.caching_room.Seed;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.graduation.farmerfriend.ecommerce_models.Product;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface SeedDao {

    @Insert
    Completable insertSeed(ArrayList<Seed> Seed);

    @Query("Select * from Seed")
    Single<List<Seed>> getSeeds();
}
