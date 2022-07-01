package com.graduation.farmerfriend.caching_room.Fert;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface FertDao {

    @Insert
    Completable insertFert(ArrayList<Fert> Fert);

    @Query("Select * from Fert")
    Single<List<Fert>> getFerts();

}
