package com.graduation.farmerfriend.repos;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.graduation.farmerfriend.apis.ForecastInterface;
import com.graduation.farmerfriend.models.ForecastModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForecastRepo {
    private static final String BOOK_SEARCH_SERVICE_BASE_URL = "http://dataservice.accuweather.com/";

    private ForecastInterface forecastInterface;
    private MutableLiveData<ForecastModel> forecastModelLiveDataLiveData;
    ForecastModel forecastModel;
    Context context;

    public ForecastRepo(Context context) {
        forecastModelLiveDataLiveData = new MutableLiveData<>();
        this.context = context;
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.level(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        forecastInterface = new retrofit2.Retrofit.Builder()
                .baseUrl(BOOK_SEARCH_SERVICE_BASE_URL)
//                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ForecastInterface.class);
    }

    public void setForecastData() {
        forecastInterface.getDailyForecast("2mCOTVxtXQjYJ7EjC8bsJIB7V9tm4WaB", "ar-eg", true)
                .enqueue(new Callback<ForecastModel>() {
                    @Override
                    public void onResponse(Call<ForecastModel> call, Response<ForecastModel> response) {
                        forecastModelLiveDataLiveData.postValue(response.body());
                        forecastModel = response.body();
                        Toast.makeText(context, String.valueOf(response.body().dailyForecasts.get(0).night.iconPhrase), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<ForecastModel> call, Throwable t) {
//                        forecastModelLiveDataLiveData.postValue(null);
                    }
                });
    }

    public LiveData<ForecastModel> getForecastModelLiveDataLiveData() {
        return forecastModelLiveDataLiveData;
    }

    public ForecastModel getForecastModel() {
        return forecastModel;
    }

}
