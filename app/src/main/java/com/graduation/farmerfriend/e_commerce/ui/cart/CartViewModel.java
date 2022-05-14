package com.graduation.farmerfriend.e_commerce.ui.cart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.graduation.farmerfriend.ecommerce_models.Cart;
import com.graduation.farmerfriend.ecommerce_models.PatchCart;
import com.graduation.farmerfriend.repos.EcommerceRepo;
import java.util.ArrayList;

import io.reactivex.rxjava3.core.Single;

public class CartViewModel extends ViewModel {
    private LiveData<ArrayList<Cart>> cartLiveData;
    private EcommerceRepo ecommerceRepo;
    public void init(){
        ecommerceRepo = EcommerceRepo.getInstance();
        cartLiveData = ecommerceRepo.getCartLiveData();
    }
    public void getCartData(String userId){
        ecommerceRepo.getCartItems(userId);
    }
    public LiveData<ArrayList<Cart>> getCartLiveData() {
        return cartLiveData;
    }
    public void changeQuantity(int cartID, ArrayList<PatchCart> patchCarts){
        ecommerceRepo.patchQuantity(cartID,patchCarts);
    }
    public void deleteProduct(int productId,String userID){
        ecommerceRepo.deleteProduct(productId,userID);
    }
}
