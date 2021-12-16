package com.graduation.farmerfriend.home;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.graduation.farmerfriend.models.Root;
import com.graduation.farmerfriend.repos.ForecastRepo;

public class ForecastViewModel extends ViewModel {
    private ForecastRepo forecastRepo;

    public void init(Context context) {
        forecastRepo = new ForecastRepo(context);
    }

    public void setForecastData() {
        forecastRepo.setForecastData();
    }

    public LiveData<Root> getForecastModelLiveData() {
        return forecastRepo.getForecastModelLiveDataLiveData();
//        return null;
    }
}
