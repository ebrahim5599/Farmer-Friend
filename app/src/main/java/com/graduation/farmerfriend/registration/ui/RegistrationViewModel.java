package com.graduation.farmerfriend.registration.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.graduation.farmerfriend.registration.data.RegisterClient;
import com.graduation.farmerfriend.registration.pojo.ForgotPassword;
import com.graduation.farmerfriend.registration.pojo.UserData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationViewModel extends ViewModel {
    MutableLiveData<UserData> userDataMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> errorMessage = new MutableLiveData<>();
    MutableLiveData<ForgotPassword> codeMessage = new MutableLiveData<>();
    MutableLiveData<String> codeMessageString = new MutableLiveData<>();

    public void sendCode(String email) {
        RegisterClient.getINSTANCE().sendCode(email).enqueue(new Callback<ForgotPassword>() {
            @Override
            public void onResponse(Call<ForgotPassword> call, Response<ForgotPassword> response) {
                if (response.body() != null)
                    codeMessage.setValue(response.body());
                else if (response.code() == 400) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.errorBody().string());
                        String userCode = jsonObject.getString("message");
                        codeMessageString.setValue(userCode);
                        Log.i("sendCode", userCode);
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ForgotPassword> call, Throwable t) {
                codeMessageString.setValue(t.getMessage());
            }
        });
    }

    public void resetPassword(String email, String otp, String password){
        RegisterClient.getINSTANCE().resetPassword(email, otp, password).enqueue(new Callback<ForgotPassword>() {
            @Override
            public void onResponse(Call<ForgotPassword> call, Response<ForgotPassword> response) {
                if (response.body() != null)
                    codeMessage.setValue(response.body());
                else if (response.code() == 400) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.errorBody().string());
                        String userCode = jsonObject.getString("message");
                        codeMessageString.setValue(userCode);
                        Log.i("sendCode", userCode);
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ForgotPassword> call, Throwable t) {
                codeMessageString.setValue(t.getMessage());

            }
        });
    }

    public void getUserDataFromLogin(String email, String password) {
        RegisterClient.getINSTANCE().getUserData_login(email, password).enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                if (response.body() != null)
                    userDataMutableLiveData.setValue(response.body());

                if (response.code() == 400){
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.errorBody().string());
                        String userMessage = jsonObject.getString("message");
                        errorMessage.setValue(userMessage);
                        Log.i("login", userMessage);
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
//                Log.i("login", response.message());
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                errorMessage.setValue(t.toString());
                Log.i("login", t.toString());
            }
        });
    }

    public void getUserDataFromRegistration(String fName, String lName, String username,
                                            String email, String password) {
        RegisterClient.getINSTANCE().getUserData_register(fName, lName, username, email, password)
                .enqueue(new Callback<UserData>() {
                    @Override
                    public void onResponse(Call<UserData> call, Response<UserData> response) {
                        if (response.body() != null)
                            userDataMutableLiveData.setValue(response.body());

                        if (response.code() == 400){
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(response.errorBody().string());
                                String userMessage = jsonObject.getString("message");
                                errorMessage.setValue(userMessage);
                                Log.i("login", userMessage);
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UserData> call, Throwable t) {
                        errorMessage.setValue(t.toString());
                        Log.i("login", t.toString());
                    }
                });
    }
}
