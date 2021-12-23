package com.graduation.farmerfriend.e_commerce.ui.cart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.ActivityUserData10Binding;
import com.graduation.farmerfriend.databinding.ActivityUserDataBinding;
import com.graduation.farmerfriend.e_commerce.ui.User_Data;
import com.graduation.farmerfriend.location.Location;

public class UserDataActivity extends AppCompatActivity {

    ActivityUserDataBinding binding;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        location = new Location(UserDataActivity.this, 1001);
        location.getLocation();
        binding.UserDataButtonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                location.getLocation();
                binding.UserDataActivityTextViewLocationAddress.setVisibility(View.VISIBLE);
                binding.UserDataActivityTextViewLocationAddress.setText(location.getAddress());
                binding.UserDataEdittextCity.setText(location.getCity());
                binding.UserDataEdittextGovernorate.setText(location.getGovernorate());
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == location.getLOCATION_REQUEST_CODE()) {
            // If request is cancelled, the result arrays are empty.
            location.getLocation();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == location.getLOCATION_REQUEST_CODE()) {

            Toast.makeText(this, "     mm", Toast.LENGTH_SHORT).show();
            if () {
                Toast.makeText(this, "afsfa", Toast.LENGTH_SHORT).show();
                // If request is cancelled, the result arrays are empty.
                location.getLocation();
            }

        }
    }
}