package com.graduation.farmerfriend.e_commerce.ui.product_categories;

import android.view.animation.LinearInterpolator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.graduation.farmerfriend.ecommerce_models.Product;
import com.graduation.farmerfriend.repos.EcommerceRepo;

import java.util.ArrayList;

public class SeedViewModel extends ViewModel {
    private EcommerceRepo ecommerceRepo;
    public void init(){
        ecommerceRepo = EcommerceRepo.getInstance();
    }


    public LiveData<ArrayList<Product>> getSeedProductsLiveData(){
        return ecommerceRepo.getSeedLiveDataProducts();
    }

    public void getEcommerceSeedProducts() {
        ecommerceRepo.getEcommerceSeedProducts();
    }

}
