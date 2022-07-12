package com.graduation.farmerfriend.caching_room.Tips;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.graduation.farmerfriend.Tips;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface TipsDao {

    @Insert
    Completable insertTips(ArrayList<Tips> tips);

    @Query("Select * from Tips")
    Single<List<Tips>> getTips();
}
