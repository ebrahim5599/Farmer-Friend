package com.graduation.farmerfriend.repos;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.graduation.farmerfriend.IOTModels.Control;
import com.graduation.farmerfriend.IOTModels.IOTRoot;
import com.graduation.farmerfriend.IOTModels.Sensors;
import com.graduation.farmerfriend.apis.IOTInterface;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class IOTRepo {

    private static final String IOT_SERVICE_BASE_URL = "https://iotfarmsystem-default-rtdb.firebaseio.com/userId0/";
    private static IOTRepo Instance;
    private final IOTInterface iotInterface;
    private final MutableLiveData<IOTRoot> iOTAllLiveData;
    private final MutableLiveData<Control> iOTControlLiveData;
    private final MutableLiveData<Sensors> iOTSensorsLiveData;


    public static IOTRepo getInstance() {
        if (Instance == null) {
            Instance = new IOTRepo();
        }
        return Instance;
    }

    private IOTRepo() {
        iOTAllLiveData = new MutableLiveData<>();
        iOTSensorsLiveData = new MutableLiveData<>();
        iOTControlLiveData = new MutableLiveData<>();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        iotInterface = new retrofit2.Retrofit.Builder()
                .baseUrl(IOT_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IOTInterface.class);
    }


    public void changeControl(Control control) {
        iotInterface.changeControl(control).enqueue(new Callback<Control>() {
            @Override
            public void onResponse(Call<Control> call, @NonNull Response<Control> response) {
                assert response.body() != null;
                Log.i("IOTData", "good put");
            }

            @Override
            public void onFailure(Call<Control> call, Throwable t) {
                Log.d("IOTData", t.toString());
            }
        });

    }


    public void getControlData() {
        Log.d("IOTData", "getIOTAllData() ");
        iotInterface.getControlData().enqueue(new Callback<Control>() {
            @Override
            public void onResponse(Call<Control> call, @NonNull Response<Control> response) {
                Log.d("IOTData", "onResponse: " + response.body());
                assert response.body() != null;
                iOTControlLiveData.postValue(response.body());
                Log.d("IOTData", String.valueOf("good call"));
            }

            @Override
            public void onFailure(Call<Control> call, Throwable t) {
                Log.d("IOTData", t.toString());
            }
        });
    }

    public void getSensorsData() {
        Log.d("IOTData", "getSensorsData() ");
        iotInterface.getSensorsData().enqueue(new Callback<Sensors>() {
            @Override
            public void onResponse(Call<Sensors> call, @NonNull Response<Sensors> response) {
                Log.d("IOTData", "onResponse: " + response.body());
                assert response.body() != null;
                iOTSensorsLiveData.postValue(response.body());
                Log.d("IOTData", String.valueOf("good call"));
            }

            @Override
            public void onFailure(Call<Sensors> call, Throwable t) {
                Log.d("IOTData", t.toString());
            }
        });
    }

    public LiveData<IOTRoot> getIOTModelLiveData() {
        return iOTAllLiveData;
    }

    public LiveData<Control> getControlLiveData() {
        return iOTControlLiveData;
    }

    public LiveData<Sensors> getSensorsLiveData() {
        return iOTSensorsLiveData;
    }
}
