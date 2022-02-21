package com.graduation.farmerfriend.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.constants.Constants;
import com.graduation.farmerfriend.databinding.ActivityMainBinding;
import com.graduation.farmerfriend.home.ForecastViewModel;
import com.graduation.farmerfriend.location.AddressCallBack;
import com.graduation.farmerfriend.location.Location;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AddressCallBack {

    private Location location;
    private SharedPreferences.Editor editor;
    ActivityMainBinding binding;
    private AppBarConfiguration appBarConfiguration;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.MAIN_SHARED_PREFERENCES, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        ForecastViewModel viewModel = new ViewModelProvider(this).get(ForecastViewModel.class);
        viewModel.init(this);
        location = new Location(this, 1002, this);
        location.getLocation();
        toolbar = binding.mainToolbar;
        setSupportActionBar(toolbar);



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.homeFragment, R.id.controlFragment, R.id.ECommerceFragment, R.id.moreFragment).build();


        // Removing shadow from bottomActionBar.

        // Navigation between fragments.
        NavController navCo = Navigation.findNavController(this, R.id.fragmentContainerView);
        NavigationUI.setupActionBarWithNavController(this, navCo, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navCo);
        navCo.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.wishlistFragment) {
//                    toolbar.setVisibility(View.GONE);
                    bottomNavigationView.setVisibility(View.GONE);
                } else if (destination.getId() == R.id.cartFragment) {
//                    toolbar.setVisibility(View.GONE);
                    bottomNavigationView.setVisibility(View.GONE);
                } else if (destination.getId() == R.id.seedProductsFragment) {
//                    toolbar.setVisibility(View.GONE);
                    bottomNavigationView.setVisibility(View.GONE);
                } else if (destination.getId() == R.id.toolProductsFragment) {
//                    toolbar.setVisibility(View.GONE);
                    bottomNavigationView.setVisibility(View.GONE);
                } else if (destination.getId() == R.id.fertilizerProductsFragment) {
//                    toolbar.setVisibility(View.GONE);
                    bottomNavigationView.setVisibility(View.GONE);
                } else if (destination.getId() == R.id.hotDealsFragment) {
//                    toolbar.setVisibility(View.GONE);
                    bottomNavigationView.setVisibility(View.GONE);
                } else if (destination.getId() == R.id.bestSellerFragment) {
//                    toolbar.setVisibility(View.GONE);
                    bottomNavigationView.setVisibility(View.GONE);
                } else if (destination.getId() == R.id.storeFragment) {
                    toolbar.setVisibility(View.GONE);
                    bottomNavigationView.setVisibility(View.GONE);
                } else if (destination.getId() == R.id.storeAddItemFragment) {
                    toolbar.setVisibility(View.GONE);
                    bottomNavigationView.setVisibility(View.GONE);
                } else if (destination.getId() == R.id.itemDescriptionFragment) {
                    toolbar.setVisibility(View.GONE);
                    bottomNavigationView.setVisibility(View.GONE);
                } else {
                    toolbar.setVisibility(View.VISIBLE);
                    bottomNavigationView.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navCo = Navigation.findNavController(this, R.id.fragmentContainerView);
        return NavigationUI.navigateUp(navCo, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.shop_main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == location.getLOCATION_REQUEST_CODE()) {
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

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "on destroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getAddress(String address) {
        String s = String.format(Locale.US, "%f,%f", location.getWayLatitude(), location.getWayLongitude());
        editor.putString(Constants.LOCATION, s);
        editor.apply();
        location.destroy();
    }

}