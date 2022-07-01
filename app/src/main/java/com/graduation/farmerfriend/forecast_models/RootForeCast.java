package com.graduation.farmerfriend.forecast_models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.graduation.farmerfriend.location.LocationPojo;

@Entity(tableName = "Weather")
public class RootForeCast {
    @PrimaryKey(autoGenerate = true)
    public int id ;

    public LocationPojo location;
    public Current current;
    public Forecast forecast;

    public RootForeCast(LocationPojo location ,Current current, Forecast forecast) {
        this.location = location;
        this.current = current;
        this.forecast = forecast;
    }

    public RootForeCast(Current tmpCurrent, Forecast tmpForecast) {
    }

    public LocationPojo getLocation() {
         return location;
    }

     public void setLocation(LocationPojo location) {
      this.location = location;
      }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }
}
