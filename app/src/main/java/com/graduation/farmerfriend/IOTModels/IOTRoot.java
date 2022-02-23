package com.graduation.farmerfriend.IOTModels;

import com.google.gson.annotations.SerializedName;

public class IOTRoot {
    @SerializedName("Control")
    public Control control;


    @SerializedName("Sensors")
    public Sensors sensors;
}
