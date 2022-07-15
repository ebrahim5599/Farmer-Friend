package com.graduation.farmerfriend.home;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.graduation.farmerfriend.Tips;
import com.graduation.farmerfriend.ecommerce_models.IOTStatus;
import com.graduation.farmerfriend.ecommerce_models.Product;
import com.graduation.farmerfriend.forecast_models.RootForeCast;
import com.graduation.farmerfriend.repos.EcommerceRepo;
import com.graduation.farmerfriend.repos.ForecastRepo;
import com.graduation.farmerfriend.repos.TipsRepo;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeViewModel extends ViewModel {
    private final ForecastRepo forecastRepo;
    private final EcommerceRepo ecommerceRepo;
    private TipsRepo tipsRepo;
    Context context;
    public HomeViewModel() {
        super();
        ecommerceRepo = EcommerceRepo.getInstance();
        forecastRepo = ForecastRepo.getInstance();
    }

    public void init(Context context){
        tipsRepo = TipsRepo.getInstance(context);
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
    public LiveData<ArrayList<Tips>> getTipsLiveData(){
        return tipsRepo.getTipsLiveData();
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        forecastRepo.onClear();
    }
    public LiveData<IOTStatus> getIOTStatusLiveData(){
        return ecommerceRepo.getIotStatusLiveData();
    }
    public void getEcommerceAllProducts() {
        ecommerceRepo.getEcommerceAllProducts();
    }
    public void checkIotStatus(String userName){
        ecommerceRepo.checkIotStatus(userName);
    }
}
