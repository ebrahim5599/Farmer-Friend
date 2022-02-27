package com.graduation.farmerfriend.apis;

import com.graduation.farmerfriend.IOTModels.Control;
import com.graduation.farmerfriend.IOTModels.IOTRoot;
import com.graduation.farmerfriend.IOTModels.Sensors;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface IOTInterface {

    @PUT("control.json")
    Call<Control> changeControl(@Body Control control);

    @GET("control.json")
    Call<Control> getControlData();
    @GET("sensors.json")
    Call<Sensors> getSensorsData();
}
