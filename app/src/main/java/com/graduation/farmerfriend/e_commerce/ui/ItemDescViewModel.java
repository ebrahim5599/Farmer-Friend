package com.graduation.farmerfriend.e_commerce.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.graduation.farmerfriend.ecommerce_models.Product;
import com.graduation.farmerfriend.repos.EcommerceRepo;

public class ItemDescViewModel extends ViewModel {
    private EcommerceRepo ecommerceRepo;
    public void init(){
        ecommerceRepo = EcommerceRepo.getInstance();
    }
    public void getProductById(int id){
        ecommerceRepo.getProductByID(id);
    }
    public LiveData<Product> getProductLiveData(){
        return ecommerceRepo.getProductLiveData();
    }
}
