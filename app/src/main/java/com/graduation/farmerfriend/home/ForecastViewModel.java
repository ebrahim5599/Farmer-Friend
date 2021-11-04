package com.graduation.farmerfriend.home;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.graduation.farmerfriend.models.ForecastModel;
import com.graduation.farmerfriend.repos.ForecastRepo;

public class ForecastViewModel extends ViewModel {
    private ForecastRepo forecastRepo;

    public void init(Context context) {
        forecastRepo = new ForecastRepo(context);

    }

    public void setForecastData() {
        forecastRepo.setForecastData();
    }

    public LiveData<ForecastModel> getForecastModelLiveData() {
        return forecastRepo.getForecastModelLiveDataLiveData();
//        return null;
    }
}
