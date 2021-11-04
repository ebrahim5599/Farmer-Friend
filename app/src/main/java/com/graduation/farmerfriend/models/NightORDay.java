package com.graduation.farmerfriend.models;

import com.google.gson.annotations.SerializedName;

public class NightORDay {

    @SerializedName("Icon")
    public int icon;
    @SerializedName("IconPhrase")
    public String iconPhrase;
    @SerializedName("HasPrecipitation")
    public boolean hasPrecipitation;
}
