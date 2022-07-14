package com.graduation.farmerfriend.apis;

import androidx.annotation.NonNull;

import com.graduation.farmerfriend.ecommerce_models.CartRoot;
import com.graduation.farmerfriend.ecommerce_models.IOTStatus;
import com.graduation.farmerfriend.ecommerce_models.PatchCart;
import com.graduation.farmerfriend.ecommerce_models.PostCart;
import com.graduation.farmerfriend.ecommerce_models.Product;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ECommerceInterface {

    // get Products APIS
    @NonNull
    @GET("/api/Products")
    Single<ArrayList<Product>> getAllProducts();

    @NonNull
    @GET("/api/Products/GetSeedProduct")
    Single<ArrayList<Product>> getSeedProducts();

    @NonNull
    @GET("/api/Products/GetToolProduct")
    Single<ArrayList<Product>> getToolProducts();

    @NonNull
    @GET("/api/Products/GetFerProduct")
    Single<ArrayList<Product>> getFerProducts();

    @NonNull
    @GET("/api/Products/{id}")
    Single<Product> getProduct(@Path("id") int productId);


    //Cart APIS
    @GET("/api/Cart/{id}")
    Single<ArrayList<CartRoot>> getCartProducts(@Path("id") String userId);
    @PATCH("/api/Cart/{id}")
    Single<Object> patchCartProductQuantity(@Path("id") int cartItemsId, @Body @NonNull ArrayList<PatchCart> patchCarts);
    @DELETE("/api/Cart/{cartItemsId}")
    Single<Object> deleteProductFromCart(@Path("cartItemsId") int cartItemsId);
    @POST("/api/Cart")
    Single<PostCart> postProductToCart(@NonNull @Body  PostCart postCart);

    // get IOT Status
    @GET("/api/Auth/GetHasIotVal/{username}")
    Single<IOTStatus>getIotStatus(@Path("username") @NonNull String userName);
    @PATCH("/api/Auth/{username}")
    Single<Object>changeIotStatus(@Path("username") String userName, @Body ArrayList<Data_HasIoT> data_hasIoT);
}