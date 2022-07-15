package com.graduation.farmerfriend.more.upload_photo;

import com.graduation.farmerfriend.ecommerce_models.Product;

import io.reactivex.rxjava3.core.Single;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface APIPhoto {

    @Multipart
    @POST("api/ImageUpload/Save/{id}")
    Call<ResponseBody> uploadphoto (@Part MultipartBody.Part photo, @Path("id") String id);

    @GET("api/ImageUpload/GetprofileImage/{userId}")
    Single<String> getProduct(@Path("userId") String url);

   @DELETE("api/ImageUpload/Delete/{id}")
   Single<String> deleteProductFromCart(@Path("id") String url);
}
