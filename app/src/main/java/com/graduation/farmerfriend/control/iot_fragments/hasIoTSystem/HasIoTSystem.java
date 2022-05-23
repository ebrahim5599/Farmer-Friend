package com.graduation.farmerfriend.control.iot_fragments.hasIoTSystem;

import com.graduation.farmerfriend.control.iot_fragments.Mail.User_Data_Mail;
import com.graduation.farmerfriend.ecommerce_models.PatchCart;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PATCH;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface HasIoTSystem {

    @PATCH("api/Auth/{UserName}")
    Single<Object> Has_IoT(@Path("UserName") String username , @Body ArrayList<Data_HasIoT > Data_HasIoT  ) ;
}
