package com.graduation.farmerfriend.apis;

import com.graduation.farmerfriend.IOTModels.IOTRoot;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;

public interface IOTInterface {
    @PATCH("/UserId0.json")
    Call<IOTRoot> put(@Body IOTRoot iotRoot);

//    @POST("/UserId0.json")
//    Call<IOTRoot> postForFirstTime();

    @GET("/UserId0.json")
    Call<IOTRoot> getIOTData();
}
