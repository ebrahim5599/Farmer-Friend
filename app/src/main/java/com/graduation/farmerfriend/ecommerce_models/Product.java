package com.graduation.farmerfriend.ecommerce_models;

import java.util.ArrayList;

public class Product{
    public int productId;
    public String productName;
    public String vendorId;
    public int categoryId;
    public Object isActive;
    public Object isDelete;
    public String createdDate;
    public String modifiedDate;
    public String description;
    public String productImage;
    public Object isFeatured;
    public int quantity;
    public double price;
    public Object vendor;
    public Object category;
    public ArrayList<Object> tblCarts;
}
