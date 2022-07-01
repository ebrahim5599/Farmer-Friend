package com.graduation.farmerfriend.repos;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.graduation.farmerfriend.apis.ForecastInterface;
import com.graduation.farmerfriend.forecast_models.RootForeCast;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForecastRepo {
    private static final String WEATHER_SERVICE_BASE_URL = " https://api.weatherapi.com";
    private static ForecastRepo Instance;
    private ForecastInterface forecastInterface;
    private MutableLiveData<RootForeCast> forecastModelLiveDataLiveData;
    private static final String TAG = "ForecastRepo";
    private CompositeDisposable compositeDisposable;


    public static ForecastRepo getInstance() {
        if (Instance == null) {
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
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ForecastInterface.class);

    }

    public void setForecastData(String location) {
        Log.d("setForecastData", "00");
//        forecastInterface.getCurrentForecast("ac1763cd50fd42cd9fe131850210912",location,"en",3).enqueue(new Callback<Root>() {
//            @Override
//            public void onResponse(Call<Root> call, Response<Root> response) {
//                forecastModelLiveDataLiveData.postValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<Root> call, Throwable t) {
//
//            }
//        });
        Single<RootForeCast> forecastObservable = forecastInterface.getCurrentForecast("ac1763cd50fd42cd9fe131850210912", location, "en", 3)
                .subscribeOn(Schedulers.io());
        compositeDisposable = new CompositeDisposable();


        SingleObserver<RootForeCast> forecastObserver = new SingleObserver<RootForeCast>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull RootForeCast root) {
                forecastModelLiveDataLiveData.postValue(root);
            }
            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
            }
        };

        forecastObservable.subscribe(forecastObserver);

    }

    public LiveData<RootForeCast> getForecastModelLiveData() {
        return forecastModelLiveDataLiveData;
    }

    public void onClear() {
        compositeDisposable.clear();
    }

    private int compenda(RootForeCast rootForeCast1, RootForeCast rootForeCast2) {
        String root12 = rootForeCast1.forecast.getForecastDay().get(0).date + rootForeCast2.forecast.forecastday.get(0).date;
        return 5;
    }
}
