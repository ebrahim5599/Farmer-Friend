package com.graduation.farmerfriend.control.iot_fragments.Mail;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Mailing {
    @POST("api/Mailing/send")
    public Call<User_Data_Mail> sendmail(@Body User_Data_Mail user_data) ;
}