package com.graduation.farmerfriend.IOT.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.graduation.farmerfriend.IOTModels.IOTRoot;
import com.graduation.farmerfriend.repos.IOTRepo;

public class IOTViewModel extends ViewModel {
    private IOTRepo iotRepo;
    private LiveData<IOTRoot> iotRootLiveData;


    public void init() {
        iotRepo = IOTRepo.getInstance();
        iotRootLiveData = iotRepo.getIOTModelLiveData();
    }


    public LiveData<IOTRoot> getIOTModelLiveData(){
        return iotRootLiveData;
    }
    public void getIOTData() {
        iotRepo.getIOTAllData();
    }

}