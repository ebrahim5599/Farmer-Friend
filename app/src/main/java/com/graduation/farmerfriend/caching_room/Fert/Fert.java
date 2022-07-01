package com.graduation.farmerfriend.caching_room.Fert;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Fert")
public class Fert {

    @PrimaryKey
    public int productId;
    public String productName;
    public String description;
    public String productImage;
    public double price;
}
