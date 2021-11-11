package com.graduation.farmerfriend.e_commerce.ui.deals;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.ActivityDealsBinding;

public class DealsActivity extends AppCompatActivity {

    private ActivityDealsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDealsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}