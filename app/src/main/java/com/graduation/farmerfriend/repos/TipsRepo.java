package com.graduation.farmerfriend.repos;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.graduation.farmerfriend.Tips;
import com.graduation.farmerfriend.caching_room.Tips.TipsDatabase;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TipsRepo {

    private final DatabaseReference referenceTips;
    private final MutableLiveData<ArrayList<Tips>> tipsLiveData;
    private static final String TAG = "TipsRepo";
    TipsDatabase tipsDatabase;
    Boolean internet ;
    Context context ;

    public TipsRepo(Context context) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        referenceTips = database.getReference().child("tips");
        tipsLiveData = new MutableLiveData<>();
        getData();
        this.context = context ;
        tipsDatabase = TipsDatabase.getInstance(context);
        if(getConnectivityStatus(context)){
            tipsDatabase.tipsDao().getTips()
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(tips -> { tipsLiveData.postValue((ArrayList<Tips>)tips);} , throwable -> {});

        }
    }


    private void getData() {
        referenceTips.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Log.d(TAG, "onDataChanged: " + snapshot.getKey());
                    Log.d(TAG, "onDataChanged: " + snapshot.getValue());
                    ArrayList<Tips> tips = new ArrayList<>();

//                 snapshot.getChildren().forEach(dataSnapshot ->tips.add(0,dataSnapshot.getValue(Tips.class)));
                    for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                        Tips tip = datasnapshot.getValue(Tips.class);
                        tips.add(0, tip);
//                    Log.d(TAG, "onDataChanged: " + datasnapshot.getValue(Tips.class).getContent());
                    }
                    tipsLiveData.postValue(tips);

                    tipsDatabase.tipsDao().insertTips(tips)
                            .subscribeOn(Schedulers.io())
                            .subscribe(new CompletableObserver() {
                                @Override
                                public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                                }

                                @Override
                                public void onComplete() {
                                    Log.d(TAG, "yes");
                                }

                                @Override
                                public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                                }
                            });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public MutableLiveData<ArrayList<Tips>> getTipsLiveData() {
        return tipsLiveData;
    }

    public Boolean getConnectivityStatus(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                internet = true ;
                return internet;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                internet = true ;
                return internet;
            }
        } else {
            internet = false ;
            return internet;
        }
        return internet;
    }
}
