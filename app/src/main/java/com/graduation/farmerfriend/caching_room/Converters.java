
package com.graduation.farmerfriend.caching_room;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.graduation.farmerfriend.forecast_models.Current;
import com.graduation.farmerfriend.forecast_models.Forecast;
import com.graduation.farmerfriend.location.Location;
import com.graduation.farmerfriend.location.LocationPojo;

public class Converters {

    @TypeConverter
    public String fromLocationPojoToGson(LocationPojo location){
        return new Gson().toJson(location);
    }

    @TypeConverter
    public LocationPojo fromGsonToLocationPojo(String string){
        return new Gson().fromJson(string,LocationPojo.class);
    }

    @TypeConverter
    public String fromCurrentToGson(Current current){
        return new Gson().toJson(current);
    }

    @TypeConverter
    public Current fromGsonToCurrent(String string){
        return new Gson().fromJson(string,Current.class);
    }

    @TypeConverter
    public String fromForecastToGson(Forecast forecast){
        return new Gson().toJson(forecast);
    }

    @TypeConverter
    public Forecast fromGsonToForecast(String string){
        return new Gson().fromJson(string,Forecast.class);
    }
}
