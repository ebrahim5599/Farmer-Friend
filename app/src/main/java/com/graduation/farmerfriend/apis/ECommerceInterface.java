package com.graduation.farmerfriend.apis;

import com.graduation.farmerfriend.ecommerce_models.Product;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ECommerceInterface {
    @GET("/api/Products")
    Single<ArrayList<Product>> getAllProducts();
    @GET("/api/Products/GetSeedProduct")
    Single<ArrayList<Product>> getSeedProducts();
    @GET("/api/Products/GetToolProduct")
    Single<ArrayList<Product>> getToolProducts();
    @GET("/api/Products/GetFerProduct")
    Single<ArrayList<Product>> getFerProducts();
    @GET("/api/Products/{id}")
    Single<Product> getProduct(@Path("id") int productId);
}
