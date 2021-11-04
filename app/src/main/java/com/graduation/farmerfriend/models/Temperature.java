package com.graduation.farmerfriend.models;

import com.google.gson.annotations.SerializedName;

public class Temperature{
    @SerializedName("Minimum")
    public MaximumORMinimum minimum;
    @SerializedName("Maximum")
    public MaximumORMinimum maximum;
}