package com.graduation.farmerfriend.apis;




import com.graduation.farmerfriend.models.Root;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ForecastInterface {
    @GET("/v1/forecast.json")
    Call<Root> getCurrentForecast(
            @Query("key") String key,
            @Query("q") String q,
            @Query("lang") String language,
            @Query("days") int days
    );
}
