package com.graduation.farmerfriend.IOTModels;

import com.google.gson.annotations.SerializedName;

public class Sensors {
    @SerializedName("Smoisture")
    public int smoisture;
    @SerializedName("Sph")
    public int sph;
    @SerializedName("Stemp")
    public int stemp;
    @SerializedName("Whumidity")
    public int whumidity;
    @SerializedName("Wluminous")
    public double wluminous;
    @SerializedName("Wpressure")
    public int wpressure;
    @SerializedName("Wtemp")
    public int wtemp;
}
