package com.graduation.farmerfriend.ui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.graduation.farmerfriend.ecommerce_models.IOTStatus;
import com.graduation.farmerfriend.repos.EcommerceRepo;
import com.graduation.farmerfriend.repos.ForecastRepo;

import org.tensorflow.lite.support.metadata.schema.Content;

public class MainActivityViewModel extends ViewModel {
    private ForecastRepo forecastRepo;
    private EcommerceRepo ecommerceRepo;

    public void init(Context context) {
        forecastRepo = ForecastRepo.getInstance();
        ecommerceRepo = EcommerceRepo.getInstance();
        ecommerceRepo.init(context);
    }

    public void setForecastData(String location) {
        forecastRepo.setForecastData(location);
    }

    public void getEcommerceAllProducts() {
        ecommerceRepo.getEcommerceAllProducts();
    }

    public void getEcommerceSeedProducts() {
        ecommerceRepo.getEcommerceSeedProducts();
    }

    public void getEcommerceFerProducts() {
        ecommerceRepo.getEcommerceFerProducts();
    }

    public void getEcommerceToolProducts(){
        ecommerceRepo.getEcommerceToolProducts();
    }
    public void checkIotStatus(String userName){
        ecommerceRepo.checkIotStatus(userName);
    }

    @NonNull
    public LiveData<IOTStatus> getIotStatusLiveData(){
        return ecommerceRepo.getIotStatusLiveData();
    }


}
