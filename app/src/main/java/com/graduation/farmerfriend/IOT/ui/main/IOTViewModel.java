package com.graduation.farmerfriend.IOT.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.graduation.farmerfriend.IOTModels.Control;
import com.graduation.farmerfriend.IOTModels.IOTRoot;
import com.graduation.farmerfriend.IOTModels.Sensors;
import com.graduation.farmerfriend.repos.IOTRepo;

import java.util.List;

public class IOTViewModel extends ViewModel {
    private IOTRepo iotRepo;
    private LiveData<Control> iotControlLiveData;
    private LiveData<Sensors> iotSensorsLiveData;

    public void init() {
        iotRepo = IOTRepo.getInstance();
        iotControlLiveData = iotRepo.getControlLiveData();
        iotSensorsLiveData = iotRepo.getSensorsLiveData();
    }

    public LiveData<Control> getIOTControlLiveData(){
        return iotControlLiveData;
    }

    public LiveData<Sensors> getIOTSensorsLiveData(){ return iotSensorsLiveData; }
}