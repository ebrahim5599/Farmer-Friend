package com.graduation.farmerfriend.ui;

import androidx.lifecycle.ViewModel;

import com.graduation.farmerfriend.repos.EcommerceRepo;
import com.graduation.farmerfriend.repos.ForecastRepo;

public class MainActivityViewModel extends ViewModel {
    private ForecastRepo forecastRepo;
    private EcommerceRepo ecommerceRepo;

    public void init() {
        forecastRepo = ForecastRepo.getInstance();
        ecommerceRepo = EcommerceRepo.getInstance();
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

}
