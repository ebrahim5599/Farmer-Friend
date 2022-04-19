package com.graduation.farmerfriend.registration.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.graduation.farmerfriend.registration.data.RegisterClient;
import com.graduation.farmerfriend.registration.pojo.UserData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationViewModel extends ViewModel {
    MutableLiveData<UserData> userDataMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public void getUserDataFromLogin(String email, String password){
        RegisterClient.getINSTANCE().getUserData_login(email, password).enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                if (response.body() != null)
                    userDataMutableLiveData.setValue(response.body());
                else{
                    errorMessage.setValue("Email or Password is incorrect!");
                }
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {

            }
        });
    }

    public void getUserDataFromRegistration(String fName, String lName, String username,
                                            String email, String password) {
        RegisterClient.getINSTANCE().getUserData_register(fName,lName,username,email,password)
                .enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                if (response.body() != null)
                    userDataMutableLiveData.setValue(response.body());
                else{
                    errorMessage.setValue("Email or Password is incorrect!");
                }
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {

            }
        });
    }
}
