package com.graduation.farmerfriend.apis;




import com.graduation.farmerfriend.forecast_models.RootForeCast;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ForecastInterface {
    @GET("/v1/forecast.json")
//    getplace();
    Single<RootForeCast> getCurrentForecast(
            @Query("key") String key,
            @Query("q") String q,
            @Query("lang") String language,
            @Query("days") int days
    );
}
