package com.graduation.farmerfriend.ecommerce_models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "Products")
public class Product{

    @PrimaryKey
    public int productId;
    public String productName;
    public String description;
    public String productImage;
    public double price;
    public int categoryId;

}
