package com.graduation.farmerfriend.location;

import android.app.Activity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;

public class LocationPojo {

    public double wayLatitude;
    public double wayLongitude;
    public String[] permissions;
    public String addressText = "";
    public String governorate;
    public String city;
    public String country;

    public double getWayLatitude() {
        return wayLatitude;
    }

    public void setWayLatitude(double wayLatitude) {
        this.wayLatitude = wayLatitude;
    }

    public double getWayLongitude() {
        return wayLongitude;
    }

    public void setWayLongitude(double wayLongitude) {
        this.wayLongitude = wayLongitude;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }

    public String getAddressText() {
        return addressText;
    }

    public void setAddressText(String addressText) {
        this.addressText = addressText;
    }

    public String getGovernorate() {
        return governorate;
    }

    public void setGovernorate(String governorate) {
        this.governorate = governorate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
