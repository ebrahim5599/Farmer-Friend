package com.graduation.farmerfriend.e_commerce.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.graduation.farmerfriend.ecommerce_models.Product;
import com.graduation.farmerfriend.repos.EcommerceRepo;

import java.util.ArrayList;

public class EcommerceFragmentViewModel extends ViewModel {
    private EcommerceRepo ecommerceRepo;

    public void init() {
        ecommerceRepo = EcommerceRepo.getInstance();
    }

    public LiveData<ArrayList<Product>> getFerProductsLiveData() {
        return ecommerceRepo.getFerLiveDataProducts();
    }

    public LiveData<ArrayList<Product>> getSeedProductsLiveData(){
        return ecommerceRepo.getSeedLiveDataProducts();
    }

    public LiveData<ArrayList<Product>> getToolProductsLiveData() {
        return ecommerceRepo.getToolLiveDataProducts();
    }
    public LiveData<ArrayList<Product>> getAllProductsLiveData(){
        return  ecommerceRepo.getAllLiveDataProducts();
    }
}
