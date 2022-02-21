package com.graduation.farmerfriend.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.constants.Constants;
import com.graduation.farmerfriend.home.ForecastViewModel;
import com.graduation.farmerfriend.location.AddressCallBack;
import com.graduation.farmerfriend.location.Location;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AddressCallBack {

    private Location location;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_FarmerFriend);
        setContentView(R.layout.activity_main);

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.MAIN_SHARED_PREFERENCES,MODE_PRIVATE);
        editor= sharedPreferences.edit();

        ForecastViewModel viewModel = new ViewModelProvider(this).get(ForecastViewModel.class);
        viewModel.init(this);
        location = new Location(this,1002,this);
        location.getLocation();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Removing shadow from bottomActionBar.
        bottomNavigationView.setBackground(null);

        // Navigation between fragments.
        NavHostFragment navHostFragment = (NavHostFragment)
                getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView);
        assert navHostFragment != null;
        NavController navCo = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView, navCo);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == location.getLOCATION_REQUEST_CODE()){
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted. Continue the action or workflow
                // in your app.
                location.getLocation();
            }
//            else {
//                // Explain to the user that the feature is unavailable because
//                // the features requires a permission that the user has denied.
//                // At the same time, respect the user's decision. Don't link to
//                // system settings in an effort to convince the user to change
//                // their decision.
//            }
        }


                return;


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "on destroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getAddress(String address) {
        String s = String.format(Locale.US,"%f,%f",location.getWayLatitude(),location.getWayLongitude());
        editor.putString(Constants.LOCATION,s);
        editor.apply();
        location.destroy();


    }
}