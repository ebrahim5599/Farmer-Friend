package com.graduation.farmerfriend.models;

import java.util.List;

public class Forecast{
    public List<Forecastday> forecastday;

    public List<Forecastday> getForecastday() {
        return forecastday;
    }

    public void setForecastday(List<Forecastday> forecastday) {
        this.forecastday = forecastday;
    }
}
