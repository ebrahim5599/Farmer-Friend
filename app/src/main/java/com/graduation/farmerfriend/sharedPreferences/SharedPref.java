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

    public boolean getBoolPref(String key) {
       return sharedPreferences.getBoolean(key,false);
    }
    public void putBoolPref(String key,boolean value){
        editor.putBoolean(key,value).apply();
    }

    public Integer getIntPref(String key ){return  sharedPreferences.getInt(key,0);}
    public void putIntPref(String key , Integer value){editor.putInt(key,value).apply();}

}
