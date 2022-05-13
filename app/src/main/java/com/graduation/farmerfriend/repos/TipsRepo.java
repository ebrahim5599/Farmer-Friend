package com.graduation.farmerfriend.repos;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.graduation.farmerfriend.Tips;

import java.util.ArrayList;
import java.util.HashMap;

public class TipsRepo {

    private final DatabaseReference referenceTips;
    private final MutableLiveData<ArrayList<Tips>> tipsLiveData;
    private static final String TAG = "TipsRepo";

    public TipsRepo() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        referenceTips = database.getReference().child("tips");
        tipsLiveData = new MutableLiveData<>();
        getData();
    }

    private void getData() {
        referenceTips.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Log.d(TAG, "onDataChanged: " + snapshot.getKey());
                Log.d(TAG, "onDataChanged: " + snapshot.getValue());
                ArrayList<Tips> tips = new ArrayList<>();
                
//
//                 snapshot.getChildren().forEach(dataSnapshot ->tips.add(0,dataSnapshot.getValue(Tips.class)));
                for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                    Tips tip = datasnapshot.getValue(Tips.class);
                    tips.add(0,tip);
//                    Log.d(TAG, "onDataChanged: " + datasnapshot.getValue(Tips.class).getContent());
                }
                tipsLiveData.postValue(tips);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public MutableLiveData<ArrayList<Tips>> getTipsLiveData() {
        return tipsLiveData;
    }

}
