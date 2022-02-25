package com.graduation.farmerfriend.apis;

import com.graduation.farmerfriend.IOTModels.IOTRoot;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface IOTInterface {
    @PUT("/userId0.json")
    Call<IOTRoot> put(@Body IOTRoot iotRoot);

//    @POST("/UserId0.json")
//    Call<IOTRoot> postForFirstTime();

    @GET("/userId0.json")
    Call<IOTRoot> getIOTData();
}
