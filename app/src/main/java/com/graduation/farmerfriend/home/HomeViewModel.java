package com.graduation.farmerfriend.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.graduation.farmerfriend.ecommerce_models.Product;
import com.graduation.farmerfriend.forecast_models.RootForeCast;
import com.graduation.farmerfriend.repos.EcommerceRepo;
import com.graduation.farmerfriend.repos.ForecastRepo;

import java.util.ArrayList;


public class HomeViewModel extends ViewModel {
    private final ForecastRepo forecastRepo;
    private final EcommerceRepo ecommerceRepo;

    public HomeViewModel() {
        super();
        ecommerceRepo = EcommerceRepo.getInstance();
        forecastRepo = ForecastRepo.getInstance();
    }


    public void setForecastData(String location) {
        forecastRepo.setForecastData(location);
    }

    public LiveData<RootForeCast> getForecastModelLiveData() {
        return forecastRepo.getForecastModelLiveData();
    }
    public LiveData<ArrayList<Product>> getAllProductsLiveData(){
        return ecommerceRepo.getAllLiveDataProducts();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        forecastRepo.onClear();
    }
}
