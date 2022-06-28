package com.graduation.farmerfriend.ecommerce_models;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import retrofit2.http.Body;

public class TblProduct {
    int productId;
    @NonNull
    public String productName;
    @NonNull
    public String vendorId;
    public int categoryId;
    public boolean isActive;
    public boolean isDelete;
    @NonNull
    public String createdDate;
    @NonNull
    public String modifiedDate;
    @NonNull
    public String description;
    @NonNull
    public String productImage;
    public boolean isFeatured;
    public int quantity;
    public double price;
    public Object discountId;
    public Object discount;
    public Object vendor;
    public Object category;
    @NonNull
    public ArrayList<Object> cartItems;
    @NonNull
    public ArrayList<Object> orderItems;

}
