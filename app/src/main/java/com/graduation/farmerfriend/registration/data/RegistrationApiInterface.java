package com.graduation.farmerfriend.registration.data;

import androidx.annotation.NonNull;

import com.graduation.farmerfriend.registration.pojo.ForgotPassword;
import com.graduation.farmerfriend.registration.pojo.NullClass;
import com.graduation.farmerfriend.registration.pojo.UserData;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RegistrationApiInterface {

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("api/Auth/register")
    public Call<UserData> userRegistration(@Body HashMap<Object, Object> map);

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("api/Auth/login")
    public Call<UserData> userLogin(@Body HashMap<Object, Object> map);

    @POST("Account/SendPasswordResetCode")
    public Call<ForgotPassword> sendCode(@Query("email") String email);

    @POST("Account/ResetPassword")
    public Call<ForgotPassword> resetPassword(
            @Query("email") String email,
            @Query("otp") String otp,
            @Query("newPassword") String newPassword
    );

    /* حسبي الله ونعم الوكيل */
//    @POST("Account/SendPasswordResetCode?email=ebrahem2512011@gmail.com")
//    public Call<ForgotPassword> sendCode();

//    @FormUrlEncoded
//    @POST("Account/SendPasswordResetCode")
//    public Call<ForgotPassword> sendCode(
//            @FieldMap @NonNull HashMap<String, Object> map);

//    @FormUrlEncoded
//    @POST("Account/SendPasswordResetCode")
//    public Call<ForgotPassword> sendCode(
//            @Field("email") String email,
//            Callback<ForgotPassword> callback
//    );

//    @Headers({"Accept: application/json", "Content-Type: application/json"})
//    @FormUrlEncoded
//    @POST("Account/ResetPassword")
//    public Call<NullClass> resetPassword(
//            @Field("email") String email,
//            @Field("otp") String otp,
//            @Field("newPassword") String newPassword
//    );
}
