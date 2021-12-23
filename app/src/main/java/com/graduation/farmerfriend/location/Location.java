package com.graduation.farmerfriend.location;

import android.Manifest;
import android.app.Activity;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.disklrucache.DiskLruCache;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.e_commerce.ui.cart.UserDataActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Location {
    private final int LOCATION_REQUEST_CODE;

    private double wayLatitude;
    private double wayLongitude;
    private Activity activity;
    private final LocationCallback locationCallback;
    private final LocationRequest locationRequest;
    private String[] permissions;
    private FusedLocationProviderClient mFusedLocationClient;
    private Geocoder geocoder;
    private String addressText = "";
    private String governorate;
    private String city;

    public Location(Activity activity, int locationRequestCode) {
        this.activity = activity;
        LOCATION_REQUEST_CODE = locationRequestCode;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                for (android.location.Location location : locationResult.getLocations()) {
                    if (location != null) {
                        wayLatitude = location.getLatitude();
                        wayLongitude = location.getLongitude();
                    }
                }
            }
        };
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(1000);

    }

    public double getWayLatitude() {
        return wayLatitude;
    }

    public double getWayLongitude() {
        return wayLongitude;
    }


    public void getLocation() {

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        SettingsClient client = LocationServices.getSettingsClient(activity);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());
        task.addOnSuccessListener(activity, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(@NonNull LocationSettingsResponse locationSettingsResponse) {
                if (ActivityCompat.checkSelfPermission(activity,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
                    ActivityCompat.requestPermissions(activity, permissions, LOCATION_REQUEST_CODE);

                } else {
                    mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, activity.getMainLooper());
//                      if(mFusedLocationClient != null){
//                            mFusedLocationClient.removeLocationUpdates(locationCallback);
//                      }
                }
            }
        });
        task.addOnFailureListener(activity, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    // Location settings are not satisfied, but this can be fixed
                    // by showing the user a dialog.
                    try {
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        resolvable.startResolutionForResult(activity,
                                1000);
                    } catch (IntentSender.SendIntentException sendEx) {
                        // Ignore the error.
                    }
                }
            }
        });
    }


    public int getLOCATION_REQUEST_CODE() {
        return LOCATION_REQUEST_CODE;
    }

    public String getAddress() {
        try {
            Toast.makeText(activity, wayLatitude+"", Toast.LENGTH_SHORT).show();
            geocoder = new Geocoder(activity, Locale.US);
            List<Address> listAddress = geocoder.getFromLocation(wayLatitude, wayLongitude, 1);

            if (listAddress.size() > 0) {
                Address address = listAddress.get(0);
                governorate = address.getAdminArea();
                city = address.getSubAdminArea();
                addressText = " your address is :\n" + address.getAddressLine(0);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return addressText;
    }
    public String getGovernorate() {
        return governorate;
    }

    public String getCity() {
        return city;
    }



}
