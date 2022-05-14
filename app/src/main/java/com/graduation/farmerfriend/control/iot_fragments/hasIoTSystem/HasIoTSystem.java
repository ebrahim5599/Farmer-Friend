package com.graduation.farmerfriend.control.iot_fragments.hasIoTSystem;

import com.graduation.farmerfriend.control.iot_fragments.Mail.User_Data_Mail;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PATCH;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface HasIoTSystem {

    @PATCH("api/Auth/{UserName}")
    public Call<Data_HasIoT> Has_IoT(@Path("UserName") String username , @Body Data_HasIoT data_hasIoT ) ;
}
