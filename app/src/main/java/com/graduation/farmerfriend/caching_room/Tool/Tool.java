package com.graduation.farmerfriend.caching_room.Tool;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Tool")
public class Tool {

    @PrimaryKey
    public int productId;
    public String productName;
    public String description;
    public String productImage;
    public double price;
}
