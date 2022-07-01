package com.graduation.farmerfriend.e_commerce.ui.product_categories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.graduation.farmerfriend.caching_room.Tool.Tool;
import com.graduation.farmerfriend.ecommerce_models.Product;
import com.graduation.farmerfriend.repos.EcommerceRepo;

import java.util.ArrayList;

public class ToolsViewModel extends ViewModel {
    private EcommerceRepo ecommerceRepo;

    public void init() {
        ecommerceRepo = EcommerceRepo.getInstance();
    }

    public LiveData<ArrayList<Tool>> getToolProductsLiveData() {
        return ecommerceRepo.getToolLiveDataProducts();
    }
}
