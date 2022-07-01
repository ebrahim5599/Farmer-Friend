package com.graduation.farmerfriend.e_commerce.ui.product_categories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.graduation.farmerfriend.caching_room.Fert.Fert;
import com.graduation.farmerfriend.ecommerce_models.Product;
import com.graduation.farmerfriend.repos.EcommerceRepo;

import java.util.ArrayList;

public class FertilizersViewModel extends ViewModel {
    private EcommerceRepo ecommerceRepo;

    public void init() {
        ecommerceRepo = EcommerceRepo.getInstance();
    }

    public LiveData<ArrayList<Fert>> getFerProductsLiveData() {
        return ecommerceRepo.getFerLiveDataProducts();
    }


    public void getEcommerceFerProducts() {
        ecommerceRepo.getEcommerceFerProducts();
    }

}
