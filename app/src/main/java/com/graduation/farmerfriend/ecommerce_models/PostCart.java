package com.graduation.farmerfriend.ecommerce_models;

public class PostCart {
    int productId ;
    String memberId;

    public PostCart(int productId, String memberId) {
        this.productId = productId;
        this.memberId = memberId;
    }
}
