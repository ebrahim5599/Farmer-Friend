package com.graduation.farmerfriend.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.customview.widget.Openable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.control.ControlFragment;
import com.graduation.farmerfriend.home.HomeFragment;
import com.graduation.farmerfriend.location.Location;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

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
        Location location = new Location(this,1000);

        if(requestCode == location.getLOCATION_REQUEST_CODE()){
            // If request is cancelled, the result arrays are empty.
            location.getLocation();
        }

    }
}