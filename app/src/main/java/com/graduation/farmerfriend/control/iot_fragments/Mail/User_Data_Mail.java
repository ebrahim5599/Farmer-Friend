package com.graduation.farmerfriend.control.iot_fragments.Mail;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class User_Data_Mail {

    private String Subject ;
    private String Body ;


    public User_Data_Mail(String Subject , String Body ) {
        this.Subject = Subject;
        this.Body = Body ;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }
}
