package com.graduation.farmerfriend.apis;

import com.graduation.farmerfriend.ecommerce_models.Cart;
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
    @GET("/api/Cart/{id}")
    Single<ArrayList<Cart>> getCartItems(@Path("id") String userId);
    @PATCH("/api/Cart/{id}")
    Single<Object> changeQuantity(@Path("id") int id, @Body ArrayList<PatchCart> patchCarts);
    @DELETE("/api/Cart/{productId}/{userId}")
    Single<Object> deleteProductFromCart(@Path("productId") int productID,@Path("userId") String userId);
    @POST("/api/Cart")
    Single<PostCart> addToCart(@Body PostCart postCart);
}
