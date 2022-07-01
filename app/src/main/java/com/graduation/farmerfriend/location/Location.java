package com.graduation.farmerfriend.location;

import android.Manifest;
import android.app.Activity;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.Task;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Location {

    private final int LOCATION_REQUEST_CODE;
    private final Activity activity;
    private final LocationCallback locationCallback;
    private final LocationRequest locationRequest;
    private final FusedLocationProviderClient mFusedLocationClient;

    LocationPojo locationPojo = new LocationPojo();

    public Location(Activity activity, int locationRequestCode) {
        this.activity = activity;
        LOCATION_REQUEST_CODE = locationRequestCode;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                for (android.location.Location location : locationResult.getLocations()) {
                    if (location != null) {
                        locationPojo.wayLatitude = location.getLatitude();
                        locationPojo.wayLongitude = location.getLongitude();
                        getAddress();
                    }
                }
            }
        };
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(1000);

    }

    public Location(Activity activity, int locationRequestCode, AddressCallBack addressCallBack) {
        this.activity = activity;
        LOCATION_REQUEST_CODE = locationRequestCode;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);

        locationCallback = new LocationCallback() {

            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                for (android.location.Location location : locationResult.getLocations()) {
                    if (location != null) {
                        locationPojo.wayLatitude = location.getLatitude();
                        locationPojo.wayLongitude = location.getLongitude();
                        getAddress();
                        addressCallBack.getAddress(getAddressText());
                    }
                }
            }
        };
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(1000);


    }

    public double getWayLatitude() {
        return locationPojo.wayLatitude;
    }

    public double getWayLongitude() {
        return locationPojo.wayLongitude;
    }


    public void getLocation() {

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        SettingsClient client = LocationServices.getSettingsClient(activity);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());
        task.addOnSuccessListener(activity, locationSettingsResponse -> {
            if (ActivityCompat.checkSelfPermission(activity,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                locationPojo.permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
                ActivityCompat.requestPermissions(activity, locationPojo.permissions, LOCATION_REQUEST_CODE);

            } else {
                mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, activity.getMainLooper());
            }
        });
        task.addOnFailureListener(activity, e -> {
            if (e instanceof ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    ResolvableApiException resolvable = (ResolvableApiException) e;
                    resolvable.startResolutionForResult(activity,
                            LOCATION_REQUEST_CODE);
                } catch (IntentSender.SendIntentException sendEx) {
                    // Ignore the error.
                }
            }
        });
    }


    public int getLOCATION_REQUEST_CODE() {
        return LOCATION_REQUEST_CODE;
    }

    public void getAddress() {
        try {
            Geocoder geocoder = new Geocoder(activity, Locale.US);
            List<Address> listAddress = geocoder.getFromLocation(locationPojo.wayLatitude, locationPojo.wayLongitude, 1);

            if (listAddress.size() > 0) {
                Address address = listAddress.get(0);
                locationPojo.governorate = address.getAdminArea();
                locationPojo.city = address.getSubAdminArea();
                locationPojo.country = address.getCountryName();

                locationPojo.addressText = " your address is :\n" + address.getAddressLine(0);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getAddressText() {
        return locationPojo.addressText;
    }

    public String getGovernorate() {
        return locationPojo.governorate;
    }

    public String getCity() {
        return locationPojo.city;
    }

    public String getCountry() {
        return locationPojo.country;
    }

    public void destroy() {
        mFusedLocationClient.removeLocationUpdates(locationCallback).addOnFailureListener( e-> mFusedLocationClient.removeLocationUpdates(locationCallback));
    }

}
