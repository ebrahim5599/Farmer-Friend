package com.graduation.farmerfriend.registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.constants.Constants;
import com.graduation.farmerfriend.home.ForecastViewModel;
import com.graduation.farmerfriend.ui.MainActivity;

public class SplashScreenActivity extends AppCompatActivity {

    ForecastViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        viewModel = new ViewModelProvider(this).get(ForecastViewModel.class);
        viewModel.init(this);
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.MAIN_SHARED_PREFERENCES,MODE_PRIVATE);

        viewModel.setForecastData(sharedPreferences.getString(Constants.LOCATION,"Cairo"));


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        },3000);
    }
}