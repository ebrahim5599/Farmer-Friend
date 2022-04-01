package com.graduation.farmerfriend.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.graduation.farmerfriend.models.Root;
import com.graduation.farmerfriend.repos.ForecastRepo;

public class ForecastViewModel extends ViewModel {
    private ForecastRepo forecastRepo;

    public ForecastViewModel() {
        super();
        forecastRepo = ForecastRepo.getInstance();
    }



    public void setForecastData(String location) {
        forecastRepo.setForecastData(location);
    }

    public LiveData<Root> getForecastModelLiveData() {
        return forecastRepo.getForecastModelLiveData();
//        return null;
    }


}
