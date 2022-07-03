package com.graduation.farmerfriend.registration.data;
import android.util.Log;

import com.graduation.farmerfriend.registration.pojo.ForgotPassword;
import com.graduation.farmerfriend.registration.pojo.UserData;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterClient {

    private static final String BASE_URL = "http://newweb19992022-001-site1.ftempurl.com/";
    private static RegisterClient INSTANCE;
    private RegistrationApiInterface registrationApiInterface;
    private HashMap<Object, Object> loginMap, RegistrationMap;
    private HashMap<String, Object> forgotPasswordMap;

    private String email, password;


    public RegisterClient() {

        loginMap = new HashMap<>();
        RegistrationMap = new HashMap<>();
        forgotPasswordMap = new HashMap<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        registrationApiInterface = retrofit.create(RegistrationApiInterface.class);
    }

    public static RegisterClient getINSTANCE() {
        if(INSTANCE == null)
            INSTANCE = new RegisterClient();
        return INSTANCE;
    }

    public Call<UserData> getUserData_login(String email, String password){
        loginMap.put("email",email);
        loginMap.put("password",password);
        return registrationApiInterface.userLogin(loginMap);
    }

    public Call<UserData> getUserData_register(String fName, String lName, String username,
                                               String email, String password){
        RegistrationMap.put("firstName",fName);
        RegistrationMap.put("lastName",lName);
        RegistrationMap.put("username",username);
        RegistrationMap.put("email",email);
        RegistrationMap.put("password",password);
        return registrationApiInterface.userRegistration(RegistrationMap);
    }

    public Call<ForgotPassword> resetPassword(String email, String otp, String newPassword){
        return registrationApiInterface.resetPassword(email, otp, newPassword);
    }

//    public Call<ForgotPassword> sendCode(String email){
//        Log.i("sendCode", "RegisterClient "+ email);
//        return registrationApiInterface.sendCode(email);
//    }

    public Call<ForgotPassword> sendCode(String email){
        return registrationApiInterface.sendCode(email);
    }
}
