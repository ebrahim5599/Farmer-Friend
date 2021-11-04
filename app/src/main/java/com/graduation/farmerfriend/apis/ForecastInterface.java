package com.graduation.farmerfriend.apis;


import com.graduation.farmerfriend.models.ForecastModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ForecastInterface {
    @GET("/forecasts/v1/daily/1day/127164")
    Call<ForecastModel> getDailyForecast(
            @Query("apikey") String query,
            @Query("language") String language,
            @Query("metric") boolean metric
    );
}
