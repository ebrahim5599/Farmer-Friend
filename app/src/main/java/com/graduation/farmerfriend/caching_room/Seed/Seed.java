package com.graduation.farmerfriend.caching_room.Seed;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Seed")
public class Seed {

    @PrimaryKey
    public int productId;
    public String productName;
    public String description;
    public String productImage;
    public double price;
}
