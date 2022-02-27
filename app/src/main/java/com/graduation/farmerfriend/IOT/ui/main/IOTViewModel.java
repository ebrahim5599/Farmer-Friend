package com.graduation.farmerfriend.IOT.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.graduation.farmerfriend.IOTModels.Control;
import com.graduation.farmerfriend.IOTModels.IOTRoot;
import com.graduation.farmerfriend.IOTModels.Sensors;
import com.graduation.farmerfriend.repos.IOTRepo;

public class IOTViewModel extends ViewModel {
    private IOTRepo iotRepo;
    private LiveData<IOTRoot> iotRootLiveData;
    private LiveData<Control> iotControlLiveData;
    private LiveData<Sensors> iotSensorsLiveData;


    public void init() {
        iotRepo = IOTRepo.getInstance();
        iotRootLiveData = iotRepo.getIOTModelLiveData();
        iotControlLiveData = iotRepo.getControlLiveData();
        iotSensorsLiveData = iotRepo.getSensorsLiveData();
    }

    public LiveData<Control> getIOTControlLiveData(){
        return iotControlLiveData;
    }
    public LiveData<Sensors> getIOTSensorsLiveData(){
        return iotSensorsLiveData;
    }


    public void getControlData() {
        iotRepo.getControlData();
    }
    public void getSensorsData() {
        iotRepo.getSensorsData();
    }

}