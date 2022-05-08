package com.graduation.farmerfriend.e_commerce.search.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SearchResultPojo {
    public Integer productId;
    public String productName;
    public Integer categoryId;
    public String createdDate;
    public String modifiedDate;
    public Object description;
    @SerializedName("productImage")
    public String productImage;
    public Object isFeatured;
    public Integer quantity;
    public Double price;
    public Object category;
    public ArrayList<Object> tblCarts;

    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public Object getDescription() {
        return description;
    }

    public String getProductImage() {
        return productImage;
    }

    public Object getIsFeatured() {
        return isFeatured;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public Object getCategory() {
        return category;
    }

    public ArrayList<Object> getTblCarts() {
        return tblCarts;
    }
}
