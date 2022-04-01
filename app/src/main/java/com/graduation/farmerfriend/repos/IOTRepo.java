package com.graduation.farmerfriend.repos;

import android.util.Log;

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


public class IOTRepo {
    private FirebaseDatabase database ;
    private DatabaseReference referencecontrol ;
    private DatabaseReference referencesensors ;
    private final MutableLiveData<Control> iOTControlLiveData ;
    private final MutableLiveData<Sensors> iOTSensorsLiveData ;
    private static IOTRepo Instance ;


    public static IOTRepo getInstance() {
        if (Instance == null) {
            Instance = new IOTRepo();
        }
        return Instance;
    }

    private IOTRepo() {
        database = FirebaseDatabase.getInstance();
        referencecontrol = database.getReference("userId0").child("control");
        referencesensors = database.getReference("userId0").child("sensors");
        iOTControlLiveData = new MutableLiveData<>();
        iOTSensorsLiveData =  new MutableLiveData<>();

        ReadData();

    }

    public void WriteDataAuto(Boolean value){
        referencecontrol.child("isAuto").setValue(value);
    }

    public void WriteDataWater(Boolean value){
        referencecontrol.child("waterSwitch").setValue(value);
    }

    public void WriteDataFertilizer(Boolean value){
        referencecontrol.child("fertSwitch").setValue(value);
    }

    public void ReadData() {

        referencecontrol.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                iOTControlLiveData.setValue(snapshot.getValue(Control.class));
                Log.i("onchanged",snapshot.getValue(Control.class).fertSwitch+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        referencesensors.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                iOTSensorsLiveData.setValue(snapshot.getValue(Sensors.class));
                Log.i("onchanged",snapshot.getValue(Sensors.class).airTemp+"");
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
