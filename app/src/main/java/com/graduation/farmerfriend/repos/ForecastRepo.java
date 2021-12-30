package com.graduation.farmerfriend.repos;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.graduation.farmerfriend.apis.ForecastInterface;
import com.graduation.farmerfriend.models.Forecast;
import com.graduation.farmerfriend.models.Root;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForecastRepo {
    private static final String WEATHER_SERVICE_BASE_URL = " http://api.weatherapi.com";
    private static ForecastRepo Instance;

    private ForecastInterface forecastInterface;
    private MutableLiveData<Root> forecastModelLiveDataLiveData;
    Root forecastModel;


    public static ForecastRepo getInstance(){
        if(Instance == null){
            Instance = new ForecastRepo();
        }
        return Instance;
    }

    public ForecastRepo() {
        forecastModelLiveDataLiveData = new MutableLiveData<>();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        forecastInterface = new retrofit2.Retrofit.Builder()
                .baseUrl(WEATHER_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ForecastInterface.class);
    }

    public void setForecastData(String location) {
        Log.d("setForecastData","00");
        forecastInterface.getCurrentForecast("ac1763cd50fd42cd9fe131850210912",location,"en",3).enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                forecastModelLiveDataLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });
    }

    public LiveData<Root> getForecastModelLiveData() {
        return forecastModelLiveDataLiveData;
    }

    public Root getForecastModel() {
        return forecastModel;
    }

}
