package com.graduation.farmerfriend.e_commerce.ui.cart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.ActivityUserData10Binding;
import com.graduation.farmerfriend.databinding.ActivityUserDataBinding;

public class UserDataActivity extends AppCompatActivity {

    ActivityUserDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}