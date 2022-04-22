package com.graduation.farmerfriend.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public SharedPref(Context context, String prefKey) {
        this.context = context;
       sharedPreferences = context.getSharedPreferences(prefKey, Context.MODE_PRIVATE);
       editor = sharedPreferences.edit();
    }

    public String getStringPref(String key,String defaultValue) {
       return sharedPreferences.getString(key,defaultValue);
    }
    public void putStringPref(String key,String value){
        editor.putString(key,value).apply();
    }
}