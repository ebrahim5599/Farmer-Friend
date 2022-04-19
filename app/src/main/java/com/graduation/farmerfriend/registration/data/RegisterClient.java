package com.graduation.farmerfriend.registration.data;
import com.graduation.farmerfriend.registration.pojo.UserData;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterClient {
    private static final String BASE_URL = "http://teamweb2022-001-site1.itempurl.com/";
    private static RegisterClient INSTANCE;
    private RegistrationApiInterface registrationApiInterface;
    private HashMap<Object, Object> loginMap, RegistrationMap;

    private String email, password;


    public RegisterClient() {

        loginMap = new HashMap<>();
        RegistrationMap = new HashMap<>();

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
}
