package com.graduation.farmerfriend.forecast_models;

import java.util.List;

public class Forecast{
    public List<Forecastday> forecastday;

    public List<Forecastday> getForecastDay() {
        return forecastday;
    }

    public void setForecastDay(List<Forecastday> forecastday) {
        this.forecastday = forecastday;
    }
}
