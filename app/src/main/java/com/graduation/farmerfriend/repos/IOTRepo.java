package com.graduation.farmerfriend.repos;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.graduation.farmerfriend.IOTModels.IOTRoot;
import com.graduation.farmerfriend.apis.IOTInterface;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class IOTRepo {

    private static final String IOT_SERVICE_BASE_URL = "https://iotfarmsystem-default-rtdb.firebaseio.com";
    private static IOTRepo Instance;
    private final IOTInterface iotInterface;
    private final MutableLiveData<IOTRoot> iOTAllLiveData;


    public static IOTRepo getInstance() {
        if (Instance == null) {
            Instance = new IOTRepo();
        }
        return Instance;
    }

    private IOTRepo() {
        iOTAllLiveData = new MutableLiveData<>();
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

    public void putIOTData(IOTRoot iotRoot) {
        Log.d("IOTData", "putIOTData()");
        iotInterface.put(iotRoot).enqueue(new Callback<IOTRoot>() {
            @Override
            public void onResponse(@NonNull Call<IOTRoot> call, @NonNull Response<IOTRoot> response) {
                assert response.body() != null;
                Log.i("IOTData", String.valueOf("good put"));
            }

            @Override
            public void onFailure(@NonNull Call<IOTRoot> call, @NonNull Throwable t) {
                Log.d("IOTData", t.toString());
            }
        });
    }

    public void getIOTAllData() {
        Log.d("IOTData", "getIOTAllData() ");
        iotInterface.getIOTData().enqueue(new Callback<IOTRoot>() {
            @Override
            public void onResponse(Call<IOTRoot> call, @NonNull Response<IOTRoot> response) {
                Log.d("IOTData", "onResponse: "+response.body());
                assert response.body() != null;
                iOTAllLiveData.postValue(response.body());
                Log.d("IOTData", String.valueOf("good call"));
            }

            @Override
            public void onFailure(Call<IOTRoot> call, Throwable t) {
                Log.d("IOTData", t.toString());
            }
        });
    }

    public LiveData<IOTRoot> getIOTModelLiveData() {
        return iOTAllLiveData;
    }
}
