package com.graduation.farmerfriend.apis;




import com.graduation.farmerfriend.models.Root;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ForecastInterface {
    @GET("/v1/forecast.json")
//    getplace();
    Single<Root> getCurrentForecast(
            @Query("key") String key,
            @Query("q") String q,
            @Query("lang") String language,
            @Query("days") int days
    );
}
