package com.graduation.farmerfriend.IOT.ui.main;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.graduation.farmerfriend.IOTModels.Control;
import com.graduation.farmerfriend.IOTModels.Sensors;
import com.graduation.farmerfriend.constants.Constants;
import com.graduation.farmerfriend.repos.IOTRepo;
import com.graduation.farmerfriend.sharedPreferences.SharedPref;

public class IOTViewModel extends ViewModel {
    private IOTRepo iotRepo;
    private LiveData<Control> iotControlLiveData;
    private LiveData<Sensors> iotSensorsLiveData;
    private SharedPref sharedPref;

    public void init(Context context) {
        sharedPref = new SharedPref(context, Constants.MAIN_SHARED_PREFERENCES);
        String IotRef = sharedPref.getStringPref(Constants.USER_ID);
        if(!IotRef.isEmpty()){
        iotRepo = IOTRepo.getInstance(context);
        iotControlLiveData = iotRepo.getControlLiveData();
        iotSensorsLiveData = iotRepo.getSensorsLiveData();
        }
    }

    public LiveData<Control> getIOTControlLiveData(){
        return iotControlLiveData;
    }

    public LiveData<Sensors> getIOTSensorsLiveData(){ return iotSensorsLiveData; }
}