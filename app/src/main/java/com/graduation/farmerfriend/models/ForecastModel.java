package com.graduation.farmerfriend.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastModel {
    @SerializedName("Headline")
    public Headline headline;
    @SerializedName("DailyForecasts")
    public List<DailyForecast> dailyForecasts;
}
