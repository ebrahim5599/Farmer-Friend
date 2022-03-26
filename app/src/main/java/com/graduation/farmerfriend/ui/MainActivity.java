package com.graduation.farmerfriend.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.graduation.farmerfriend.IOT.ui.main.IOTViewModel;
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
    private DatabaseReference mDatabase;
    private static final String TAG = "MainActivity";
    private static final String DEBUG_TAG = "NetworkStatusExample";
    private boolean isConnected = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_FarmerFriend);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.MAIN_SHARED_PREFERENCES, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        ForecastViewModel viewModel = new ViewModelProvider(this).get(ForecastViewModel.class);
        viewModel.setForecastData(sharedPreferences.getString(Constants.LOCATION, "Cairo"));

        IOTViewModel mViewModel = new ViewModelProvider(this).get(IOTViewModel.class);
        mViewModel.init();

        location = new Location(this, 1002, this);
        location.getLocation();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ConnectivityManager connectivityManager = getSystemService(ConnectivityManager.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() {
                @Override
                public void onAvailable(Network network) {
                    Log.e(TAG, "The default network is now: " + network);
                }

                @Override
                public void onLost(Network network) {
                    Log.e(TAG, "The application no longer has a default network. The last default network was " + network);
                }

                @Override
                public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                    if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                            && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)) {
                        Handler handler = new Handler(Looper.getMainLooper());
                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                isConnected = true;
                                binding.mainActivityNoInternetConnection.setVisibility(View.GONE);
                                binding.fragmentContainerView.setVisibility(View.VISIBLE);
                            }
                        };
                        handler.post(runnable);
//                        handler.removeCallbacks(runnable);
                    }
                    Log.e(TAG, "The default network changed capabilities: " + networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED));

                }

                @Override
                public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
                    Log.e(TAG, "The default network changed link properties: " + linkProperties);
                }
            });
        }
//        ConnectivityManager connMgr =
//                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        boolean isWifiConn = false;
//        boolean isMobileConn = false;
//        for (Network network : connMgr.getAllNetworks()) {
//            NetworkInfo networkInfo = connMgr.getNetworkInfo(network);
//            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
//                isWifiConn |= networkInfo.isConnected();
//            }
//            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
//                isMobileConn |= networkInfo.isConnected();
//            }
//        }
//        Log.d(DEBUG_TAG, "Wifi connected: " + isWifiConn);
//        Log.d(DEBUG_TAG, "Mobile connected: " + isMobileConn);

        toolbar = binding.mainToolbar;
        setSupportActionBar(toolbar);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.homeFragment, R.id.controlFragment, R.id.cameraFragment, R.id.ECommerceFragment, R.id.moreFragment).build();

        // Removing shadow from bottomActionBar.

        // Navigation between fragments.
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        assert navHostFragment != null;
        NavController navCo = navHostFragment.getNavController();
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
                } else if (destination.getId() == R.id.userDataFragment) {
                    toolbar.setVisibility(View.GONE);
                    bottomNavigationView.setVisibility(View.GONE);
                } else {
                    toolbar.setVisibility(View.VISIBLE);
                    bottomNavigationView.setVisibility(View.VISIBLE);
                }
                if (!isConnected) {
                    if (destination.getId() == R.id.cameraFragment) {
                        binding.fragmentContainerView.setVisibility(View.VISIBLE);
                        binding.mainActivityNoInternetConnection.setVisibility((View.GONE));
                    }
                    else {
                        binding.fragmentContainerView.setVisibility(View.GONE);
                        binding.mainActivityNoInternetConnection.setVisibility((View.VISIBLE));
                    }
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
    }

    @Override
    public void getAddress(String address) {
        String lat_lang = String.format(Locale.US, "%f,%f", location.getWayLatitude(), location.getWayLongitude());
        String address_CC = String.format(Locale.US, "%s,%s", location.getCountry(), location.getCity());
        editor.putString(Constants.LOCATION, lat_lang);
        editor.putString(Constants.LOCATION_ADDRESS, address_CC);
        editor.apply();
        location.destroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == location.getLOCATION_REQUEST_CODE()) {
            if (requestCode == location.getLOCATION_REQUEST_CODE()) {
                // If request is cancelled, the result arrays are empty.
                location.getLocation();
            }

        }
    }


}