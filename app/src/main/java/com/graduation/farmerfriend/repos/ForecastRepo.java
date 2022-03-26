package com.graduation.farmerfriend.repos;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.apis.ForecastInterface;
import com.graduation.farmerfriend.models.Root;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForecastRepo {
    private static final String WEATHER_SERVICE_BASE_URL = " https://api.weatherapi.com";
    private static ForecastRepo Instance;
    private ForecastInterface forecastInterface;
    private MutableLiveData<Root> forecastModelLiveDataLiveData;
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
        Single<Root> forecastObservable = forecastInterface.getCurrentForecast("ac1763cd50fd42cd9fe131850210912", location, "en", 3)
                .subscribeOn(Schedulers.io());
        compositeDisposable = new CompositeDisposable();
////        Observable.zip(forecastObservable,forecastObservable,new BiFunction<Root, Root,String>() {
////
////            @Override
////            public String apply(Root root, Root root2) throws Throwable {
////                return "";
////            }
////        }).subscribe();
//        Observable.zip(forecastObservable,forecastObservable,(root, root2) -> compenda(root,root2)).subscribe();


        SingleObserver<Root> forecastObserver = new SingleObserver<Root>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull Root root) {
                forecastModelLiveDataLiveData.postValue(root);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
            }
        };
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                compositeDisposable.add(d);
//            }
//
//            @Override
//            public void onNext(Root root) {
//                forecastModelLiveDataLiveData.postValue(root);
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.d(TAG, "onError: "+e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//
//            }

        forecastObservable.subscribe(forecastObserver);

    }

    public LiveData<Root> getForecastModelLiveData() {
        return forecastModelLiveDataLiveData;
    }

    public void onClear() {
        compositeDisposable.clear();
    }

    private int compenda(Root root1, Root root2) {
        String root12 = root1.forecast.getForecastday().get(0).date + root2.forecast.forecastday.get(0).date;
        return 5;
    }
}
