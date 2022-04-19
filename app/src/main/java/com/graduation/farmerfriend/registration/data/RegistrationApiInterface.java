package com.graduation.farmerfriend.registration.data;

import com.graduation.farmerfriend.registration.pojo.UserData;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RegistrationApiInterface {

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("api/Auth/register")
    public Call<UserData> userRegistration(@Body HashMap<Object, Object> map);

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("api/Auth/login")
    public Call<UserData> userLogin(@Body HashMap<Object, Object> map);
}
