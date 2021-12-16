package com.graduation.farmerfriend.repos;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.graduation.farmerfriend.apis.ForecastInterface;
import com.graduation.farmerfriend.models.Root;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForecastRepo {
    private static final String WEATHER_SERVICE_BASE_URL = " http://api.weatherapi.com";

    private ForecastInterface forecastInterface;
    private MutableLiveData<Root> forecastModelLiveDataLiveData;
    Root forecastModel;
    Context context;

    public ForecastRepo(Context context) {
        forecastModelLiveDataLiveData = new MutableLiveData<>();
        this.context = context;
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.level(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        forecastInterface = new retrofit2.Retrofit.Builder()
                .baseUrl(WEATHER_SERVICE_BASE_URL)
//                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ForecastInterface.class);
    }

    public void setForecastData() {
        forecastInterface.getCurrentForecast("ac1763cd50fd42cd9fe131850210912","zaqaziq","en",3).enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                forecastModelLiveDataLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });
    }

    public LiveData<Root> getForecastModelLiveDataLiveData() {
        return forecastModelLiveDataLiveData;
    }

    public Root getForecastModel() {
        return forecastModel;
    }

}
