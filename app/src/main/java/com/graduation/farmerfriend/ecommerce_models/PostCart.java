package com.graduation.farmerfriend.ecommerce_models;

import androidx.annotation.NonNull;

public class PostCart {
    int productId ;
    String userId;
    int quantity;
    public PostCart(int productId, @NonNull String userId,int quantity) {
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
    }
}