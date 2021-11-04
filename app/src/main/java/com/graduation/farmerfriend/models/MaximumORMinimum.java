package com.graduation.farmerfriend.models;

import com.google.gson.annotations.SerializedName;

public class MaximumORMinimum {
    @SerializedName("Value")
    public double value;
    @SerializedName("Unit")
    public String unit;
    @SerializedName("UnitType")
    public int unitType;
}
