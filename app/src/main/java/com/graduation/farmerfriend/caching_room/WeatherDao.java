package com.graduation.farmerfriend.caching_room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.graduation.farmerfriend.forecast_models.RootForeCast;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.rxjava3.core.Completable;

@Dao
public interface WeatherDao {

    @Insert
    Completable insertWeather(RootForeCast rootForeCast);

    @Query("Select * from Weather")
    Single<List<RootForeCast>> getWeather();

}
