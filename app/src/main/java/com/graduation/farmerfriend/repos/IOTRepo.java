package com.graduation.farmerfriend.repos;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.graduation.farmerfriend.IOTModels.Control;
import com.graduation.farmerfriend.IOTModels.Sensors;
import com.graduation.farmerfriend.constants.Constants;
import com.graduation.farmerfriend.sharedPreferences.SharedPref;


public class IOTRepo {
    Context context;
    private FirebaseDatabase database;
    private DatabaseReference referenceControl;
    private final DatabaseReference referenceSensors;
    private final MutableLiveData<Control> iOTControlLiveData;
    final MutableLiveData<Sensors> iOTSensorsLiveData;
    private static IOTRepo Instance;
    private static final String TAG = "IOTRepo";


    public static IOTRepo getInstance(Context context) {
        if (Instance == null) {
            Instance = new IOTRepo(context);
        }
        return Instance;
    }

    private IOTRepo(Context context) {
        this.context = context;
        SharedPref sharedPref = new SharedPref(context, Constants.MAIN_SHARED_PREFERENCES);
        database = FirebaseDatabase.getInstance();
        Log.d("TAG", sharedPref.getStringPref(Constants.USER_ID));
        Toast.makeText(context, sharedPref.getStringPref(Constants.USER_ID), Toast.LENGTH_LONG).show();


//        referenceControl = database.getReference().child("userId0").child("control");
//        referenceSensors = database.getReference("userId0").child("sensors");
//        referenceControl = database.getReference().child(sharedPref.getStringPref(Constants.USER_ID, "userId0")).child("control");

        referenceControl = database.getReference(sharedPref.getStringPref(Constants.USER_ID)).child("control");
        referenceSensors = database.getReference(sharedPref.getStringPref(Constants.USER_ID)).child("sensors");
        iOTControlLiveData = new MutableLiveData<>();
        iOTSensorsLiveData = new MutableLiveData<>();
        ReadData();

    }


    public void WriteDataAuto(Boolean value) {
        referenceControl.child("isAuto").setValue(value);
    }

    public void WriteDataWater(Boolean value) {
        referenceControl.child("waterSwitch").setValue(value);
    }

    public void WriteDataFertilizer(Boolean value) {
        referenceControl.child("fertSwitch").setValue(value);
    }

    public void ReadData() {

        referenceControl.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue(Control.class) != null)
                    iOTControlLiveData.setValue(snapshot.getValue(Control.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        referenceSensors.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue(Control.class) != null)
                    iOTSensorsLiveData.setValue(snapshot.getValue(Sensors.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    public LiveData<Control> getControlLiveData() {
        return iOTControlLiveData;
    }

    public LiveData<Sensors> getSensorsLiveData() {
        return iOTSensorsLiveData;
    }
}
