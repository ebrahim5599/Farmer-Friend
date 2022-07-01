package com.graduation.farmerfriend.caching_room.Product;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.graduation.farmerfriend.ecommerce_models.Product;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface ProductDao {

    @Insert
    Completable insertProduct(ArrayList<Product> product);

    @Query("Select * from Products")
    Single<List<Product>> getProducts();
}
